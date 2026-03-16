package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;


import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.IllegalTicketsQuantityException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class RechargeOrderTest {

    @Test
    @DisplayName("Should throw an exception when creating a order with zero tickets.")
    void shouldThrowExceptionWhenZeroTickets() {
        assertThatThrownBy(() -> new RechargeOrder(UUID.randomUUID(), 0, 0, new BigDecimal("0.0")))
                .isInstanceOf(IllegalTicketsQuantityException.class)
                .hasMessageContaining("You must buy at least one ticket.");
    }

    @Test
    @DisplayName("Should throw an exception when received a negative ticket quantity.")
    void shouldThrowExceptionWhenReceivedANegativeTicketQuantity() {
        assertThatThrownBy(() -> new RechargeOrder(
                UUID.randomUUID(),
                -10,
                5,
                new BigDecimal("0.0")
        )).isInstanceOf(IllegalTicketsQuantityException.class)
                .hasMessageContaining("Ticket quantities cannot be negative.");
    }

    @Test
    @DisplayName("Should create a order with PENDING status successfuly.")
    void shouldCreateOrderSuccessfully() {
        UUID studentId = UUID.randomUUID();

        RechargeOrder order = new RechargeOrder(studentId, 0, 10, new BigDecimal("14.50"));

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(order.getBreakfastQuantity()).isEqualTo(0);
        assertThat(order.getLunchDinnerQuantity()).isEqualTo(10);

    }

    @Test
    @DisplayName("Should change order status to APPROVED when order is pending")
    void shouldMarkAsApprovedSuccessfully() {
        RechargeOrder order = new RechargeOrder(UUID.randomUUID(), 2, 0, new BigDecimal("1.40"));

        order.markAsApproved();

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.APPROVED);
    }

}
