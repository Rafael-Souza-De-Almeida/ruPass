package com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.EmptyPasswordException;

public record Password(String value) {

    public Password {
        if (value == null || value.isBlank()) {
            throw new EmptyPasswordException("Password cannot be empty.");
        }

    }
}
