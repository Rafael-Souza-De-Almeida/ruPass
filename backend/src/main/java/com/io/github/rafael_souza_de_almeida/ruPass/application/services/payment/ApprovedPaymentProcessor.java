package com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApprovedPaymentProcessor implements PaymentStatusProcessor{

    private final StudentRepository studentRepository;

    @Override
    public boolean supports(String status) {
        return "APPROVED".equalsIgnoreCase(status);
    }

    @Override
    public void process(RechargeOrder order, String transactionId) {

        order.markAsApproved();
        order.assignTransactionId(transactionId);

        Student student = studentRepository.findById(order.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException("Student not found."));

        student.getWallet().addTickets(order.getBreakfastQuantity(), order.getLunchDinnerQuantity());

        studentRepository.save(student);

        log.info("Payment processed! balance added to student's wallet");

    }
}
