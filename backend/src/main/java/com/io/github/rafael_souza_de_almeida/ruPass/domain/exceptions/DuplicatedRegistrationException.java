package com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions;

public class DuplicatedRegistrationException extends RuntimeException {
    public DuplicatedRegistrationException(String s) {
        super(s);
    }
}
