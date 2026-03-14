package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RechargeWalletRequest(

        @NotNull(message = "Breakfast ticket quantity is required (can be 0).")
        @Min(value = 0, message = "Breakfast quantity cannot be negative.")
        Integer breakfastAmount,

        @NotNull(message = "Lunch/dinner ticket quantity is required (can be 0).")
        @Min(value = 0, message = "Lunch/dinner quantity cannot be negative.")
        Integer lunchDinnerAmount

) {
        @JsonIgnore
        @AssertTrue(message = "You must add at least one ticket to proceed with the recharge")
        public boolean isValidCart() {
                int breakfast = (breakfastAmount != null) ? breakfastAmount : 0;
                int lunch = (lunchDinnerAmount != null) ? lunchDinnerAmount : 0;

                return (breakfast + lunch) > 0;
        }
}