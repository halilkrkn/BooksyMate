package com.halilkrkn.BooksyMate.services.abstracts;

import com.halilkrkn.BooksyMate.services.dto.request.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest request);
}
