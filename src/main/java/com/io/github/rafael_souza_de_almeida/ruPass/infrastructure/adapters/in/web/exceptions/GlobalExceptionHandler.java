package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.exceptions;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.DuplicatedRegistrationException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InvalidBiometricException;
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

    @ExceptionHandler(InvalidBiometricException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidBiometricException(InvalidBiometricException ex, HttpServletRequest request) {
        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }


}
