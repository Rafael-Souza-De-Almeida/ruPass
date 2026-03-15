package com.io.github.rafael_souza_de_almeida.ruPass.domain.services;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.IllegalStudentType;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;

import java.math.BigDecimal;

public class TicketPricingService {

    private static final BigDecimal UNDERGRAD_BREAKFAST_PRICE = new BigDecimal("0.70");
    private static final BigDecimal UNDERGRAD_LUNCH_DINNER_PRICE = new BigDecimal("1.45");

    private static final BigDecimal VISITOR_BREAKFAST_PRICE = new BigDecimal("9.90");
    private static final BigDecimal VISITOR_LUNCH_DINNER_PRICE = new BigDecimal("19.90");

    public BigDecimal calculateTotal(StudentType type, int breakfastQuantity, int lunchDinnerQuantity) {

        BigDecimal breakfastPrice = switch(type) {
            case UNDERGRADUATE -> UNDERGRAD_BREAKFAST_PRICE;
            case VISITOR -> VISITOR_BREAKFAST_PRICE;
        };

        BigDecimal lunchDinnerPrice = switch(type) {
            case UNDERGRADUATE -> UNDERGRAD_LUNCH_DINNER_PRICE;
            case VISITOR -> VISITOR_LUNCH_DINNER_PRICE;
        };

        BigDecimal totalBreakfast = breakfastPrice.multiply(BigDecimal.valueOf(breakfastQuantity));
        BigDecimal totalLunchDinner = lunchDinnerPrice.multiply(BigDecimal.valueOf(lunchDinnerQuantity));

        return totalBreakfast.add(totalLunchDinner);

    }

}
