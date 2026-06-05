package com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions;

public class EmptyPasswordException extends RuntimeException {
    public EmptyPasswordException(String s) {
        super(s);
    }
}
