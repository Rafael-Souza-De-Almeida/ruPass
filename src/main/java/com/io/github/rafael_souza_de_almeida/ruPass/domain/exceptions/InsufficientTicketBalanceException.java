package com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions;

public class InsufficientTicketBalanceException extends RuntimeException {
    public InsufficientTicketBalanceException(String s) {
        super(s);
    }
}
