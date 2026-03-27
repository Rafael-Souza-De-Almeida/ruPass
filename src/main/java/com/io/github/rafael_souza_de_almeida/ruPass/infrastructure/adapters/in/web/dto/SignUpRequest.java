package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;

public record SignUpRequest(
        String fullName,
        String email,
        String password,
        String registrationNumber,
        StudentType studentType,
        String cpf
) {
}
