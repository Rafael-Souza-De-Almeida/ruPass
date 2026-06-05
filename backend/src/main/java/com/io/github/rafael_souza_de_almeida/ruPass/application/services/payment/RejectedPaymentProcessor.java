package com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RejectedPaymentProcessor implements PaymentStatusProcessor{

    @Override
    public boolean supports(String status) {
        return "REJECTED".equalsIgnoreCase(status);
    }

    @Override
    public void process(RechargeOrder order, String transactionId) {

        order.markAsRejected();
        order.assignTransactionId(transactionId);
        log.info("Payment Rejected!");
    }
}
