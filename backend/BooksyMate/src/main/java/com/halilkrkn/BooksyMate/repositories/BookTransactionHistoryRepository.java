package com.halilkrkn.BooksyMate.repositories;

import com.halilkrkn.BooksyMate.entities.history.BookTransactionHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookTransactionHistoryRepository extends JpaRepository<BookTransactionHistory, Integer> {

    @Query("""
        SELECT history
        FROM BookTransactionHistory history
        WHERE history.user.id = :userId

""")
    Page<BookTransactionHistory> findAllBorrowedBooks(Integer userId, Pageable pageable);


    @Query("""
        SELECT history
        FROM BookTransactionHistory history
        WHERE history.book.owner.id = :userId

""")
    Page<BookTransactionHistory> findAllReturnedBooks(Integer userId, Pageable pageable);
}