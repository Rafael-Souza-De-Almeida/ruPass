package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

public record SignUpRequest(
        String fullName,
        String email,
        String password,
        String registrationNumber,
        String course,
        String cpf
) {
}
