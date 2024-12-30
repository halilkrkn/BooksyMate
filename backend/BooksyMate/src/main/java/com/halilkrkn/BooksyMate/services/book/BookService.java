package com.halilkrkn.BooksyMate.services.book;

import com.halilkrkn.BooksyMate.dto.request.book.BookRequest;
import com.halilkrkn.BooksyMate.dto.response.book.BookResponse;
import com.halilkrkn.BooksyMate.dto.response.book.BorrowedBookResponse;
import com.halilkrkn.BooksyMate.dto.response.book.PageResponse;
import org.springframework.security.core.Authentication;

public interface BookService {
    Integer saveBook(BookRequest request, Authentication connectedUser);

    BookResponse findById(Integer bookId);

    PageResponse<BookResponse> findAllBooks(Integer page, Integer size, Authentication connectedUser);

    PageResponse<BookResponse> findAllBooksByOwner(Integer page, Integer size, Authentication connectedUser);

    PageResponse<BorrowedBookResponse> findAllBorrowedBooks(Integer page, Integer size, Authentication connectedUser);

    PageResponse<BorrowedBookResponse> findAllReturnedBooks(Integer page, Integer size, Authentication connectedUser);
}
