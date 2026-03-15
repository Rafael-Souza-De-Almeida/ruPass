package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.TicketCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RechargeOrder {

    private UUID id;
    private UUID studentId;
    private TicketCategory ticketCategory;
    private int tiquetQuantity;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private String transactionId;
    private LocalDateTime createdAt;





}
