package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.InsufficientTicketBalanceException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.TicketCategory;

import java.util.UUID;

public class Wallet {

    private UUID id;
    private UUID studentId;
    private int breakfastBalance;
    private int lunchDinnerBalance;

    public Wallet(UUID studentId) {
        this.id = UUID.randomUUID();
        this.studentId = studentId;
        this.lunchDinnerBalance = 0;
        this.breakfastBalance = 0;
    }

    public Wallet(UUID id, UUID studentId, int breakfastBalance, int lunchDinnerBalance) {
        this.id = id;
        this.studentId = studentId;
        this.breakfastBalance = breakfastBalance;
        this.lunchDinnerBalance = lunchDinnerBalance;
    }

    public void addTickets(int breakfastAmount, int lunchDinnerAmount) {

        if (breakfastAmount < 0 || lunchDinnerAmount < 0) {
            throw new IllegalArgumentException("Quantities cannot be negative.");
        }

        if (breakfastAmount > 0) {
            this.breakfastBalance += breakfastAmount;
        }
        if (lunchDinnerAmount > 0) {
            this.lunchDinnerBalance += lunchDinnerAmount;
        }

    }

    public void consumeTicket(TicketCategory category) {

        if(category == TicketCategory.BREAKFAST) {

            if(this.breakfastBalance <= 0) {
                throw new InsufficientTicketBalanceException("Insufficient balance for Breakfast.");
            }

            this.breakfastBalance--;
        }

        else if(category == TicketCategory.LUNCH_DINNER) {
            if(this.lunchDinnerBalance <= 0) {
                throw new InsufficientTicketBalanceException("Insufficient balance for Lunch/Dinner");
            }

            this.lunchDinnerBalance--;
        }

        else {
            throw new IllegalArgumentException("Unknown ticket category.");
        }

    }


    public UUID getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public int getBreakfastBalance() {
        return breakfastBalance;
    }

    public int getLunchDinnerBalance() {
        return lunchDinnerBalance;
    }
}
