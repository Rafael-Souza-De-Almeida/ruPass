package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record StudentRegistrationRequest(
        @NotBlank(message = "full name is required.")
        String fullName,

        @NotBlank(message = "Registration Number is required.")
        String registrationNumber,

        @NotNull(message = "Student Type can not be null.")
        StudentType studentType,

        @NotBlank(message = "CPF is required.")
        @CPF(message = "Invalid CPF.")
        String cpf
) {
}
