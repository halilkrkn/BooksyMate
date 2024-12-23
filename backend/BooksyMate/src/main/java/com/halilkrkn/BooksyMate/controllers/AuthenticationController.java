package com.halilkrkn.BooksyMate.controllers;

import com.halilkrkn.BooksyMate.services.abstracts.AuthenticationService;
import com.halilkrkn.BooksyMate.services.dto.request.RegisterRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        authenticationService.register(request);

        return ResponseEntity.accepted().build();
    }
}
