package com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CancelledPaymentProcessor implements PaymentStatusProcessor{

    @Override
    public boolean supports(String status) {
        return "CANCELLED".equalsIgnoreCase(status);
    }

    @Override
    public void process(RechargeOrder order, String transactionId) {
        order.markAsCancelled();
        order.assignTransactionId(transactionId);
        log.info("Payment Cancelled!");
    }
}
