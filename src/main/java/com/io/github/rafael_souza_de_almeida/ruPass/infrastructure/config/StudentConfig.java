package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.config;

import com.io.github.rafael_souza_de_almeida.ruPass.application.services.RechargeWalletService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.RegisterStudentService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RechargeWalletUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
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
    public RechargeWalletUseCase rechargeWalletUseCase(StudentRepository studentRepository) {
        return new RechargeWalletService(studentRepository);
    }
}
