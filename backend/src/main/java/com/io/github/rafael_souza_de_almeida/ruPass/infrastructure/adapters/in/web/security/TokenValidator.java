package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("tokenValidator")
public class TokenValidator {

    public boolean isOwner(Jwt jwt, UUID pathStudentId) {

        if(jwt == null ) return false;

        String jwtTokenId = jwt.getClaimAsString("studentId");

        return pathStudentId.toString().equals(jwtTokenId);

    }

}
