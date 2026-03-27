package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import java.util.UUID;

public record StudentWalletResponse(
        UUID id,
        int breakfastBalance,
        int lunchDinnerBalance
) {
}
