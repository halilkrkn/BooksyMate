package com.halilkrkn.BooksyMate.services.book;

import com.halilkrkn.BooksyMate.dto.request.book.BookRequest;
import com.halilkrkn.BooksyMate.dto.response.book.BookResponse;
import com.halilkrkn.BooksyMate.dto.response.book.BorrowedBookResponse;
import com.halilkrkn.BooksyMate.dto.response.book.PageResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {
    Integer saveBook(BookRequest request, Authentication connectedUser);

    BookResponse findById(Integer bookId);

    PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser);

    PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser);

    PageResponse<BorrowedBookResponse> findAllBorrowedBooks(Integer page, Integer size, Authentication connectedUser);

    PageResponse<BorrowedBookResponse> findAllReturnedBooks(Integer page, Integer size, Authentication connectedUser);

    Integer updateShareableStatus(Integer bookId, Authentication connectedUser);

    Integer updateArchivedStatus(Integer bookId, Authentication connectedUser);

    Integer borrowBook(Integer bookId, Authentication connectedUser);

    Integer returnBorrowBook(Integer bookId, Authentication connectedUser);

    Integer approveReturnBorrowBook(Integer bookId, Authentication connectedUser);

    void uploadBookCoverPicture(MultipartFile file, Authentication connectedUser, Integer bookId);
}
