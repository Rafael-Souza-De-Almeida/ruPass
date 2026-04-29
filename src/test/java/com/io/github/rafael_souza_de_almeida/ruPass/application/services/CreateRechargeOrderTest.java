package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeOrderCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Password;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.RegistrationNumber;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRechargeOrderTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RechargeOrderRepository rechargeOrderRepository;

    @Mock
    private TicketPricingService ticketPricingService;

    @InjectMocks
    private CreateRechargeOrderService createRechargeOrderService;

    private final Cpf VALID_CPF = new Cpf("12345678909");
    private final Email VALID_EMAIL = new Email("test@gmail.com");
    private final Password VALID_PASSWORD = new Password("12345678");
    private final RegistrationNumber VALID_REGISTRATION = new RegistrationNumber("202200057689");

    @Test
    @DisplayName("Should create a recharge order sucessfully")
    void shouldCreateARechargeOrderSuccessfully(){

        Student student = new Student("Maria", VALID_EMAIL, VALID_PASSWORD, VALID_REGISTRATION, StudentType.UNDERGRADUATE, VALID_CPF);

        UUID studentId = student.getId();

        RechargeOrderCommand command = new RechargeOrderCommand(studentId, 0, 10);

        BigDecimal expectedValue = new BigDecimal("14.50");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(ticketPricingService.calculateTotal(student.getStudentType(), 0, 10)).thenReturn(expectedValue);

        RechargeOrder createdOrder = createRechargeOrderService.execute(command);

        assertThat(createdOrder).isNotNull();
        assertThat(createdOrder.getStudentId()).isEqualTo(student.getId());
        assertThat(createdOrder.getBreakfastQuantity()).isEqualTo(0);
        assertThat(createdOrder.getLunchDinnerQuantity()).isEqualTo(10);
        assertThat(createdOrder.getTotalAmount()).isEqualTo(expectedValue);
        assertThat(createdOrder.getOrderStatus()).isEqualTo(OrderStatus.PENDING);

        verify(rechargeOrderRepository, times(1)).save(createdOrder);

    }

    @Test
    @DisplayName("Should throw exception when student is not found")
    void shouldThrowExceptionWhenStudentNotFound() {

        UUID fakeStudentId = UUID.randomUUID();
        RechargeOrderCommand command = new RechargeOrderCommand(fakeStudentId, 2, 3);


        when(studentRepository.findById(fakeStudentId)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> createRechargeOrderService.execute(command))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student Not found.");


        verify(rechargeOrderRepository, never()).save(any(RechargeOrder.class));
        verify(ticketPricingService, never()).calculateTotal(any(), anyInt(), anyInt());
    }

}
