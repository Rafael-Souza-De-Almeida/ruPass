package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record StudentOrderHistoryResponse(
        UUID orderId,
        LocalDateTime createdAt,
        int breakfastQuantity,
        int lunchDinnerQuantity,
        BigDecimal totalAmount,
        OrderStatus status,
        String bankTransactionId
) {
}
