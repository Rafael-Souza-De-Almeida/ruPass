package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RechargeWalletUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeWalletCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.TicketPricingService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class RechargeWalletService implements RechargeWalletUseCase {

    private final StudentRepository studentRepository;
    private final RechargeOrderRepository rechargeOrderRepository;
    private final TicketPricingService ticketPricingService;

    @Override
    public Wallet execute(RechargeWalletCommand command) {
        Student student = studentRepository.findById(command.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Student Not found."));

        BigDecimal totalAmount = ticketPricingService.calculateTotal(student.getStudentType(),
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

        student.getWallet().addTickets(command.breakfastQuantity(), command.lunchDinnerQuantity());

        studentRepository.save(student);

        return student.getWallet();

    }
}
