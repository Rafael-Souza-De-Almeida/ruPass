package com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.EmptyEmailException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InvalidEmailException;

public record Email(String value) {

    public Email {

        if(value == null || value.isBlank()) {
            throw new EmptyEmailException("Email cannot be empty.");
        }

        if (!value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidEmailException("Email invalid format: " + value);
        }

    }

}
