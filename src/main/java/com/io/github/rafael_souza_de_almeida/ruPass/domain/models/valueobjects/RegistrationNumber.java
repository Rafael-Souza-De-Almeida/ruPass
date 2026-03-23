package com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.EmptyRegistrationNumberException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InvalidRegistrationNumberFormatException;

public record RegistrationNumber(String value) {

    public RegistrationNumber {
        if (value == null || value.isBlank()) {
            throw new EmptyRegistrationNumberException("Registration number cannot be empty.");
        }

         //if (!value.matches("\\d{10}")) {
           //  throw new InvalidRegistrationNumberFormatException("Registration must be exactly 10 digits.");
         //}
    }
}
