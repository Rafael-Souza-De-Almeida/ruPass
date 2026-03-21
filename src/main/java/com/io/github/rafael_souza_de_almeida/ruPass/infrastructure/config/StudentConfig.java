package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.config;

import com.io.github.rafael_souza_de_almeida.ruPass.application.services.CreateRechargeOrderService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.GetStudentStudentOrderHistoryService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.RegisterStudentService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CreateRechargeOrderUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.StudentOrderHistoryUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.StudentRegistrationValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    public StudentRegistrationValidator studentRegistrationValidator(StudentRepository studentRepository) {
        return new StudentRegistrationValidator(studentRepository);
    }

    @Bean
    public RegisterStudentUseCase registerStudentUseCase(
            StudentRepository studentRepository,
            StudentRegistrationValidator validator) {
        return new RegisterStudentService(studentRepository, validator);
    }

    @Bean
    public StudentOrderHistoryUseCase studentOrderHistoryUseCase(
            RechargeOrderRepository rechargeOrderRepository
    ) {
        return new GetStudentStudentOrderHistoryService(rechargeOrderRepository);
    }
}
