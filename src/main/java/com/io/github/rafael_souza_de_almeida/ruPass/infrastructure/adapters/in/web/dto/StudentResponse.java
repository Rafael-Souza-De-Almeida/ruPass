package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import java.util.UUID;

public record StudentResponsne(
        UUID id,
        String fullName,
        String email,
        String registrationNumber,
        String course
) {
}
