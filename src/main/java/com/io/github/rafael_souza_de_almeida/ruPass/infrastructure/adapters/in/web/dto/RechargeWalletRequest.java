package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RechargeWalletRequest(
        @NotNull(message = "Ticket quantity is required.")
        @Min(value = 1, message = "The minimum recharge is 1 ticket.")
        int amount
) {
}
