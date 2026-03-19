package com.io.github.rafael_souza_de_almeida.ruPass.domain.services;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.CafeteriaClosedException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.TicketCategory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CafeteriaSchedule {

    public static TicketCategory determineMealByTime(LocalTime entryTime, DayOfWeek day) {

        LocalTime breakfastStart = LocalTime.of(7,0);
        LocalTime breakfastEnd = LocalTime.of(8,0);

        LocalTime lunchStart = LocalTime.of(11,30);
        LocalTime lunchEnd = LocalTime.of(13,0);

        LocalTime dinnerStart = LocalTime.of(17,30);
        LocalTime dinnerEnd = LocalTime.of(19,0);

        if(day == DayOfWeek.SUNDAY) {
            throw new CafeteriaClosedException("The cafeteria is closed. You cannot enter right now.");
        }

        if (!entryTime.isBefore(breakfastStart) && !entryTime.isAfter(breakfastEnd)) {
            return TicketCategory.BREAKFAST;
        }

        if ((!entryTime.isBefore(lunchStart) && !entryTime.isAfter(lunchEnd)) ||
                (!entryTime.isBefore(dinnerStart) && !entryTime.isAfter(dinnerEnd))) {
            return TicketCategory.LUNCH_DINNER;
        }

        throw new CafeteriaClosedException("The cafeteria is closed. You cannot enter right now.");

    }
}
