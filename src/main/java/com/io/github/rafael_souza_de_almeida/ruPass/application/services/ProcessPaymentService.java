package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment.PaymentStatusProcessor;
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

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class ProcessPaymentService implements ProcessPaymentUseCase {

    private final RechargeOrderRepository rechargeOrderRepository;
    private final StudentRepository studentRepository;
    private final List<PaymentStatusProcessor> processors;

    @Override
    public void execute(ProcessPaymentCommand command) {

        RechargeOrder order = rechargeOrderRepository.findById(command.orderId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found."));


        PaymentStatusProcessor processor = processors.stream().filter(p -> p.supports(command.status()))
                .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Unknown status: " + command.status()));

        processor.process(order, command.transactionId());

        rechargeOrderRepository.save(order);

    }
}
