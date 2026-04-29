package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CreateRechargeOrderUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeOrderCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.TicketPricingService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CreateRechargeOrderService implements CreateRechargeOrderUseCase {

    private final StudentRepository studentRepository;
    private final RechargeOrderRepository rechargeOrderRepository;
    private final TicketPricingService ticketPricingService;

    @Override
    public RechargeOrder execute(RechargeOrderCommand command) {
        Student student = studentRepository.findById(command.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Student Not found."));

        BigDecimal totalAmount = ticketPricingService.calculateTotal(
                command.breakfastQuantity(),
                command.lunchDinnerQuantity());

        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Total amount must be greater than zero.");
        }

        RechargeOrder order = new RechargeOrder(
                student.getId(),
                command.breakfastQuantity(),
                command.lunchDinnerQuantity(),
                totalAmount
        );

       rechargeOrderRepository.save(order);

       return order;

    }
}
