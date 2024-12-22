package com.halilkrkn.BooksyMate.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    Boolean isTokenValidate(String token, UserDetails userDetails);
}
