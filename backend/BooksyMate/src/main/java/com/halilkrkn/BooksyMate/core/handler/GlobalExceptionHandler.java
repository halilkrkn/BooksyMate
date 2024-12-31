package com.halilkrkn.BooksyMate.core.handler;

import com.halilkrkn.BooksyMate.core.exception.OperationNotPermittedException;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ExceptionResponse> handleException(LockedException exp) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.ACCOUNT_LOCKED.getCode())
                        .businessErrorDescription(BusinessErrorCodes.ACCOUNT_LOCKED.getDescription())
                        .errorMessage(exp.getMessage())
                        .build());

    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ExceptionResponse> handleException(DisabledException exp) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.ACCOUNT_DISABLED.getCode())
                        .businessErrorDescription(BusinessErrorCodes.ACCOUNT_DISABLED.getDescription())
                        .errorMessage(exp.getMessage())
                        .build());

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ExceptionResponse.builder()
                        .businessErrorCode(BusinessErrorCodes.BAD_CREDENTIALS.getCode())
                        .businessErrorDescription(BusinessErrorCodes.BAD_CREDENTIALS.getDescription())
                        .errorMessage(BusinessErrorCodes.BAD_CREDENTIALS.getDescription())
                        .build());

    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ExceptionResponse> handleException(MessagingException exp) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .errorMessage(exp.getMessage())
                        .build());

    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exp) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .errorMessage(exp.getMessage())
                        .build());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp) {

        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getFieldErrors()
                .forEach(error -> {
                            var errorMessage = error.getDefaultMessage();
                            errors.add(errorMessage);
                        }
                );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponse.builder()
                        .validationErrors(errors)
                        .build());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {

        exp.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.builder()
                        .businessErrorDescription("Internal Error, Contact with the system administrator")
                        .errorMessage(exp.getMessage())
                        .build());

    }

}