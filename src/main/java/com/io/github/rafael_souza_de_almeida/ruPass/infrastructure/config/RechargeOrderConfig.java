package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.config;

import com.io.github.rafael_souza_de_almeida.ruPass.application.services.CreateRechargeOrderService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.ProcessPaymentService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CreateRechargeOrderUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.ProcessPaymentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.TicketPricingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RechargeOrderConfig {

    @Bean
    public TicketPricingService ticketPricingService() {
        return new TicketPricingService();
    }

    @Bean
    public CreateRechargeOrderUseCase createRechargeOrderUseCase(
            StudentRepository studentRepository,
            RechargeOrderRepository rechargeOrderRepository,
            TicketPricingService pricingService) {
        return new CreateRechargeOrderService(studentRepository, rechargeOrderRepository, pricingService);
    }

    @Bean
    public ProcessPaymentUseCase processPaymentUseCase(
            RechargeOrderRepository orderRepository,
            StudentRepository studentRepository) {
        return new ProcessPaymentService(orderRepository, studentRepository);
    }

}
