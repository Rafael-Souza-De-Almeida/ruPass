package com.io.github.rafael_souza_de_almeida.ruPass.application.services.payment;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApprovedPaymentProcessorTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private ApprovedPaymentProcessor approvedPaymentProcessor;

    @Test
    @DisplayName("Should process approved payment and add tickets to the wallet")
    void shouldProcessApprovedPayment() {

        UUID studentId = UUID.randomUUID();
        String transactionId = "fake-transaction-id";
        RechargeOrder order = new RechargeOrder(studentId, 0, 2, new BigDecimal("2.90"));

        Student student = new Student("John doe", "123456789", StudentType.UNDERGRADUATE, "12345678909");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        approvedPaymentProcessor.process(order, transactionId);

        assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.APPROVED);
        assertThat(order.getTransactionId()).isEqualTo(transactionId);

        assertThat(student.getWallet().getBreakfastBalance()).isEqualTo(0);
        assertThat(student.getWallet().getLunchDinnerBalance()).isEqualTo(2);

        verify(studentRepository, times(1)).save(student);

    }

}
