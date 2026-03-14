package com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InvalidBiometricException;

public record FaceBiometrics(String biometricToken) {

    public FaceBiometrics {
        if(biometricToken == null || biometricToken.isBlank()){
            throw new InvalidBiometricException("Face biometric can not be blank.");
        }
    }

    public String getToken() {
        return biometricToken;
    }
}

