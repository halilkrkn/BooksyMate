package com.halilkrkn.BooksyMate.controllers;

import com.halilkrkn.BooksyMate.dto.request.auth.AuthenticateRequest;
import com.halilkrkn.BooksyMate.dto.response.auth.AuthenticateResponse;
import com.halilkrkn.BooksyMate.services.auth.AuthenticationService;
import com.halilkrkn.BooksyMate.dto.request.auth.RegistrationRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegistrationRequest request
    ) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticateResponse> authenticate(
            @RequestBody @Valid AuthenticateRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/activate-account")
    public ResponseEntity<?> activateAccount(
            @RequestParam String token
    ) throws MessagingException {
        authenticationService.activateAccount(token);
        return ResponseEntity.ok().build();
    }

}
