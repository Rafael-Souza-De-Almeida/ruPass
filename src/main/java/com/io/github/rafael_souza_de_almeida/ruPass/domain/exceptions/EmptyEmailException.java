package com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions;

public class EmptyEmailException extends RuntimeException {
    public EmptyEmailException(String s) {
        super(s);
    }
}
