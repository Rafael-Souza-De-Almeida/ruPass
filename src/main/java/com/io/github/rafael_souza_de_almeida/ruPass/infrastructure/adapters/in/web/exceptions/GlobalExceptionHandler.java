package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.exceptions;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.DuplicatedRegistrationException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InvalidBiometricException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InvalidCpfException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedRegistrationException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicatedRegistrationException(DuplicatedRegistrationException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCpfException(InvalidCpfException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidBiometricException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidBiometricException(InvalidBiometricException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
