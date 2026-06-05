package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ConsumptionRequest(
        @NotNull UUID studentId,
        @NotNull String turnstileId
) {
}
