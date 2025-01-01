package com.halilkrkn.BooksyMate.services.book;

import com.halilkrkn.BooksyMate.core.file.FileStorageService;
import com.halilkrkn.BooksyMate.core.exception.OperationNotPermittedException;
import com.halilkrkn.BooksyMate.core.mapper.book.BookMapper;
import com.halilkrkn.BooksyMate.dto.request.book.BookRequest;
import com.halilkrkn.BooksyMate.dto.response.book.BookResponse;
import com.halilkrkn.BooksyMate.dto.response.book.BorrowedBookResponse;
import com.halilkrkn.BooksyMate.dto.response.book.PageResponse;
import com.halilkrkn.BooksyMate.entities.book.Book;
import com.halilkrkn.BooksyMate.entities.history.BookTransactionHistory;
import com.halilkrkn.BooksyMate.entities.user.User;
import com.halilkrkn.BooksyMate.repositories.BookRepository;
import com.halilkrkn.BooksyMate.repositories.BookTransactionHistoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
//    private final BookTransactionHistoryService bookTransactionHistoryService;
    private final BookTransactionHistoryRepository bookTransactionHistoryRepository;
    private final BookMapper bookMapper;
    private final FileStorageService fileStorageService;

    @Override
    public Integer saveBook(BookRequest request, Authentication connectedUser) {
        var user = ((User) connectedUser.getPrincipal());
        var book = bookMapper.toBook(request);
        book.setOwner(user);
        return bookRepository.save(book).getId();
    }

    @Override
    public BookResponse findById(Integer bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::toBookResponse)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));
    }

    @Override
    public PageResponse<BookResponse> findAllBooks(
            Integer page,
            Integer size,
            Authentication connectedUser
    ) {
        var user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAllDisplayableBooks(pageable, user.getId());

        List<BookResponse> bookResponse = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return new PageResponse<BookResponse>(
                bookResponse,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    @Override
    public PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser) {
        var user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Book> books = bookRepository.findAll(BookSpecification.withOwnerId(user.getId()), pageable);

        List<BookResponse> bookResponse = books.stream()
                .map(bookMapper::toBookResponse)
                .toList();

        return new PageResponse<BookResponse>(
                bookResponse,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isFirst(),
                books.isLast()
        );
    }

    @Override
    public PageResponse<BorrowedBookResponse> findAllBorrowedBooks(Integer page, Integer size, Authentication connectedUser) {
        var user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> allBorrowedBooks = bookTransactionHistoryRepository.findAllBorrowedBooks(user.getId(), pageable);

        List<BorrowedBookResponse> bookResponse = allBorrowedBooks.stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();

        return new PageResponse<BorrowedBookResponse>(
                bookResponse,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.isFirst(),
                allBorrowedBooks.isLast()
        );
    }

    @Override
    public PageResponse<BorrowedBookResponse> findAllReturnedBooks(Integer page, Integer size, Authentication connectedUser) {
        var user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<BookTransactionHistory> allBorrowedBooks = bookTransactionHistoryRepository.findAllReturnedBooks(user.getId(), pageable);

        List<BorrowedBookResponse> bookResponse = allBorrowedBooks.stream()
                .map(bookMapper::toBorrowedBookResponse)
                .toList();

        return new PageResponse<BorrowedBookResponse>(
                bookResponse,
                allBorrowedBooks.getNumber(),
                allBorrowedBooks.getSize(),
                allBorrowedBooks.getTotalElements(),
                allBorrowedBooks.getTotalPages(),
                allBorrowedBooks.isFirst(),
                allBorrowedBooks.isLast()
        );
    }

    @Override
    public Integer updateShareableStatus(Integer bookId, Authentication connectedUser) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));

        var user = ((User) connectedUser.getPrincipal());

        if(!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot update others shareable status of a book that you do not own.");
        }

        book.setShareable(!book.isShareable());
        bookRepository.save(book);
        return bookId;
    }

    @Override
    public Integer updateArchivedStatus(Integer bookId, Authentication connectedUser) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));
        var user = ((User) connectedUser.getPrincipal());

        if(!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot update others archived status of a book that you do not own.");
        }

        book.setArchived(!book.isArchived());
        bookRepository.save(book);
        return bookId;
    }

    @Override
    public Integer borrowBook(Integer bookId, Authentication connectedUser) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));

        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot borrow a book that is not shareable or archived.");
        }

        var user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot borrow a book that you do not own.");
        }

        final boolean isAlreadyBorrowed = bookTransactionHistoryRepository.isAlreadyBorrowedByUser(bookId, user.getId());
        if(isAlreadyBorrowed) {
            throw new OperationNotPermittedException("You cannot borrow a book that you already borrowed.");
        }

        var bookTransactionHistory = BookTransactionHistory.builder()
                .user(user)
                .book(book)
                .returned(false)
                .returnApproved(false)
                .build();


        return bookTransactionHistoryRepository.save(bookTransactionHistory).getId();
    }

    @Override
    public Integer returnBorrowBook(Integer bookId, Authentication connectedUser) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));

        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot borrow a book that is not shareable or archived.");
        }

        var user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot borrow or return a book that you do not own.");
        }

        BookTransactionHistory bookTransactionHistory = bookTransactionHistoryRepository.findByBookIdAndUserId(bookId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("You cannot return a book that you did not borrow."));
        bookTransactionHistory.setReturned(true);
        return bookTransactionHistoryRepository.save(bookTransactionHistory).getId();
    }

    @Override
    public Integer approveReturnBorrowBook(Integer bookId, Authentication connectedUser) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));

        if(book.isArchived() || !book.isShareable()) {
            throw new OperationNotPermittedException("You cannot borrow a book that is not shareable or archived.");
        }

        var user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(book.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot borrow or return a book that you do not own.");
        }

        BookTransactionHistory bookTransactionHistory = bookTransactionHistoryRepository.findByBookIdAndOwnerId(bookId, user.getId())
                .orElseThrow(() -> new OperationNotPermittedException("The book is not returned yet or you are not the owner of the book."));
        bookTransactionHistory.setReturnApproved(true);
        return bookTransactionHistoryRepository.save(bookTransactionHistory).getId();
    }

    @Override
    public void uploadBookCoverPicture(MultipartFile file, Authentication connectedUser, Integer bookId) {
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("No book found with id: " + bookId));

        var user = ((User) connectedUser.getPrincipal());
        var bookCover = fileStorageService.saveFile(file,user.getId());
        book.setBookCover(bookCover);
        bookRepository.save(book);
    }


}
