package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command;

import java.util.UUID;

public record RechargeWalletCommand(
        UUID studentId,
        int amount
) {
}
