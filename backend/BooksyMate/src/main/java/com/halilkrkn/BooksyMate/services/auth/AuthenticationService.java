package com.halilkrkn.BooksyMate.services.auth;

import com.halilkrkn.BooksyMate.dto.request.auth.AuthenticateRequest;
import com.halilkrkn.BooksyMate.dto.request.auth.RegistrationRequest;
import com.halilkrkn.BooksyMate.dto.response.auth.AuthenticateResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

public interface AuthenticationService {
    void register(RegistrationRequest request) throws MessagingException;

    AuthenticateResponse authenticate(AuthenticateRequest request);

    void activateAccount(String token) throws MessagingException;
}
