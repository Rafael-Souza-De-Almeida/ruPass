package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.ProcessPaymentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.ProcessPaymentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.OrderNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final RechargeOrderRepository rechargeOrderRepository;
    private final StudentRepository studentRepository;

    @Override
    public void execute(ProcessPaymentCommand command) {

        if(!"APPROVED".equalsIgnoreCase(command.status())) {
            return;
        }

        RechargeOrder order = rechargeOrderRepository.findById(command.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found."));

        order.markAsApproved();

        order.assignTransactionId(command.transactionId());

        Student student = studentRepository.findById(order.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found."));

        student.getWallet().addTickets(order.getBreakfastQuantity(), order.getLunchDinnerQuantity());

        rechargeOrderRepository.save(order);
        studentRepository.save(student);

        log.info("Payment processed! balance added to student's wallet");

    }
}
