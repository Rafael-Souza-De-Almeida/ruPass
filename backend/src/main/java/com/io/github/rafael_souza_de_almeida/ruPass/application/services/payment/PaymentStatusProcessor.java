package com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;

public interface PaymentStatusProcessor {

    boolean supports(String status);
    void process(RechargeOrder order, String transactionId);
}
