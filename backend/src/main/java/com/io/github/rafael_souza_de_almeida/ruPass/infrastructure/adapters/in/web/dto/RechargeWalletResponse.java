package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record RechargeWalletResponse(
        UUID orderId,
        BigDecimal totalAmount,
        String status,
        String message
) {
}
