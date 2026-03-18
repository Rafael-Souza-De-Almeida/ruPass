package com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class RejectedPaymentProcessorTest {

    @InjectMocks
    private RejectedPaymentProcessor processor;

    @Test
    @DisplayName("Should process a rejected payment")
    void shouldProcessRejectedPayment() {

        String transactionId = "fake-transaction-id";

        RechargeOrder order = new RechargeOrder(UUID.randomUUID(), 0, 1, new BigDecimal("1.45"));

        processor.process(order, transactionId);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.REJECTED);
        assertThat(order.getTransactionId()).isEqualTo(transactionId);



    }



}
