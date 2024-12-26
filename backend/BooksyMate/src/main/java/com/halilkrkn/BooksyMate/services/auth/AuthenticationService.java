package com.halilkrkn.BooksyMate.services.auth;

import com.halilkrkn.BooksyMate.dto.request.RegistrationRequest;
import jakarta.mail.MessagingException;

public interface AuthenticationService {
    void register(RegistrationRequest request) throws MessagingException;
}
