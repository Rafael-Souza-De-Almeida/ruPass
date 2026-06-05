package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import java.util.UUID;

public record EditStudentRequest(
        UUID studentId,
        String fullName,
        String email,
        String password,
        String photoUrl
) {
}
