package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

public record DigitalIdResponse(
        String fullName,
        String registrationNumber,
        String course,
        String photoUrl
) {
}
