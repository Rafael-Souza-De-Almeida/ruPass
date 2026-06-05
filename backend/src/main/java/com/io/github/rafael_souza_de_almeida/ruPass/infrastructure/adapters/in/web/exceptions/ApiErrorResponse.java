package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.exceptions;

import java.time.LocalDateTime;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
