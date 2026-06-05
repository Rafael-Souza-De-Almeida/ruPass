package com.io.github.rafael_souza_de_almeida.ruPass.domain.services;


import java.math.BigDecimal;

public class TicketPricingService {

    private static final BigDecimal BREAKFAST_PRICE = new BigDecimal("0.70");
    private static final BigDecimal LUNCH_DINNER_PRICE = new BigDecimal("1.45");


    public BigDecimal calculateTotal(int breakfastQuantity, int lunchDinnerQuantity) {

        BigDecimal totalBreakfast = BREAKFAST_PRICE.multiply(BigDecimal.valueOf(breakfastQuantity));
        BigDecimal totalLunchDinner = LUNCH_DINNER_PRICE.multiply(BigDecimal.valueOf(lunchDinnerQuantity));

        return totalBreakfast.add(totalLunchDinner);

    }

}
