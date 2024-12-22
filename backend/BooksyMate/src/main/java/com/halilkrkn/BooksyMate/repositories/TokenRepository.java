package com.halilkrkn.BooksyMate.repositories;

import com.halilkrkn.BooksyMate.entities.concretes.user.Token;
import com.halilkrkn.BooksyMate.entities.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);

}
