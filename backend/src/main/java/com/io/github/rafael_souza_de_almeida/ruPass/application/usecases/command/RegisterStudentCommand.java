package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command;

public record RegisterStudentCommand(
        String fullName,
        String email,
        String password,
        String registrationNumber,
        String course,
        String cpf
) {
}
