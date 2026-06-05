package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;

import java.util.UUID;

public interface GetStudentWalletUseCase {

    Wallet execute(UUID studentId);

}
