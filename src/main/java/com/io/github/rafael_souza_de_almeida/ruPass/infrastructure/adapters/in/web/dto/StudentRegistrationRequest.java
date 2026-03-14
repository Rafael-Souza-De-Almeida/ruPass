package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record StudentRegistrationRequest(
        String fullName,
        String registrationNumber,
        StudentType studentType,
        String cpf
) {
}
