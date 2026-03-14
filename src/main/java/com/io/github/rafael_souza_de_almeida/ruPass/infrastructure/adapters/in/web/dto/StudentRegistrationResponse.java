package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import java.util.UUID;

public record StudentRegistrationResponse(
        UUID id,
        String fullName,
        String registrationNumber
) {
}
