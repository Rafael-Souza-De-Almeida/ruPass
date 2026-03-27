package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.security;

import com.io.github.rafael_souza_de_almeida.ruPass.application.ports.out.PasswordHashPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class PasswordHashAdapter implements PasswordHashPort {

    private final PasswordEncoder encoder;

    @Override
    public String encode(String password) {
        return encoder.encode(password);
    }
}
