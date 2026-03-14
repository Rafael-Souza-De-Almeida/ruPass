package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeWalletCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;

public interface RechargeWalletUseCase {

    Wallet execute(RechargeWalletCommand command);
}
