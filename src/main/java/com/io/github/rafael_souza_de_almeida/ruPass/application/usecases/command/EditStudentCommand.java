package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;

import java.util.UUID;

public record EditStudentCommand(
        UUID studentId,
        String fullName,
        String email,
        String password
) {
}
