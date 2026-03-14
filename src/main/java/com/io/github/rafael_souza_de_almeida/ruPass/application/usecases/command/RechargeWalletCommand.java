package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.TicketCategory;

import java.util.UUID;

public record RechargeWalletCommand(
        UUID studentId,
        int breakfastQuantity,
        int lunchDinnerQuantity
) {
}
