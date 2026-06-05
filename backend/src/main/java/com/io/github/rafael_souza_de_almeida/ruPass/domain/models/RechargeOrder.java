package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.IllegalTicketsQuantityException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.TicketCategory;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RechargeOrder {

    private UUID id;
    private UUID studentId;
    private int breakfastQuantity;
    private int lunchDinnerQuantity;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private String transactionId;
    private LocalDateTime createdAt;

    public RechargeOrder(UUID studentId, int breakfastQuantity, int lunchDinnerQuantity, BigDecimal totalAmount) {

        if (breakfastQuantity < 0 || lunchDinnerQuantity < 0) {
            throw new IllegalTicketsQuantityException("Ticket quantities cannot be negative.");
        }

        if (breakfastQuantity == 0 && lunchDinnerQuantity == 0) {
            throw new IllegalTicketsQuantityException("You must buy at least one ticket.");
        }

        this.id = UUID.randomUUID();
        this.studentId = studentId;
        this.breakfastQuantity = breakfastQuantity;
        this.lunchDinnerQuantity = lunchDinnerQuantity;
        this.totalAmount = totalAmount;
        this.orderStatus = OrderStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public RechargeOrder(UUID id, UUID studentId, int breakfastQuantity, int lunchDinnerQuantity, BigDecimal totalAmount, OrderStatus orderStatus, String transactionId, LocalDateTime createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.breakfastQuantity = breakfastQuantity;
        this.lunchDinnerQuantity = lunchDinnerQuantity;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.transactionId = transactionId;
        this.createdAt = createdAt;
    }

    public void assignTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void markAsApproved() {

        if(this.orderStatus != OrderStatus.PENDING) {
            throw new IllegalStateException("Only pending order can be approved");
        }

        this.orderStatus = OrderStatus.APPROVED;

    }

    public void markAsCancelled() {

        //To-do: Return the payment when it´s already approved.

        if(this.orderStatus == OrderStatus.REJECTED) {
            throw new IllegalStateException("This order was already rejected.");
        }

        this.orderStatus = OrderStatus.CANCELLED;

    }

    public void markAsRejected() {

        if(this.orderStatus == OrderStatus.APPROVED) {
            throw new IllegalStateException("This order was already paid.");
        }

        if(this.orderStatus == OrderStatus.CANCELLED) {
            throw new IllegalStateException("This order was already cancelled.");
        }

        this.orderStatus = OrderStatus.REJECTED;

    }

    public UUID getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public int getBreakfastQuantity() {
        return breakfastQuantity;
    }

    public int getLunchDinnerQuantity() {
        return lunchDinnerQuantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
