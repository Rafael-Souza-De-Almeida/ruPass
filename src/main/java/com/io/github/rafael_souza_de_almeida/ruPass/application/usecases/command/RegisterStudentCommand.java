package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;

public record RegisterStudentCommand(
        String fullName,
        String email,
        String password,
        String registrationNumber,
        StudentType studentType,
        String cpf
) {
}
