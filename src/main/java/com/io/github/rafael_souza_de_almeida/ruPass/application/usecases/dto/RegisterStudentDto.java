package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.dto;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;

public record RegisterStudentDto(
        String fullName,
        String registrationNumber,
        StudentType studentType,
        String cpf
) {
}
