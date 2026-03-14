package com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects;

import java.util.Objects;

public record Cpf(String value) {

    public Cpf {
        Objects.requireNonNull(value, "O CPF não pode ser nulo.");

        value = value.replaceAll("\\D", "");

        if (!isValid(value)) {
            throw new IllegalArgumentException("CPF inválido: " + value);
        }
    }

    public String formatted() {
        return value.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }


    private boolean isValid(String cpf) {
        if (cpf.length() != 11) return false;
        if (cpf.matches("(\\d)\\1{10}")) return false;

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++) {
                sum += (cpf.charAt(i) - '0') * (10 - i);
            }
            int remainder = 11 - (sum % 11);
            int digit1 = (remainder == 10 || remainder == 11) ? 0 : remainder;

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += (cpf.charAt(i) - '0') * (11 - i);
            }
            remainder = 11 - (sum % 11);
            int digit2 = (remainder == 10 || remainder == 11) ? 0 : remainder;

            return digit1 == (cpf.charAt(9) - '0') && digit2 == (cpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }

}
