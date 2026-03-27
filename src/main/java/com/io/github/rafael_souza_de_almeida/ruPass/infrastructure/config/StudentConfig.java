package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.config;

import com.io.github.rafael_souza_de_almeida.ruPass.application.ports.out.PasswordHashPort;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.GetStudentOrderHistoryService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.GetStudentWalletService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.RegisterStudentService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.GetStudentWalletUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.StudentOrderHistoryUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.StudentRegistrationValidator;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.security.PasswordHashAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StudentConfig {

    @Bean
    public StudentRegistrationValidator studentRegistrationValidator(StudentRepository studentRepository) {
        return new StudentRegistrationValidator(studentRepository);
    }

    @Bean
    public PasswordHashPort passwordHashPort(PasswordEncoder encoder) {
        return new PasswordHashAdapter(encoder);
    }

    @Bean
    public GetStudentWalletUseCase getStudentWalletUseCase(StudentRepository studentRepository) {
        return new GetStudentWalletService(studentRepository);
    }

    @Bean
    public RegisterStudentUseCase registerStudentUseCase(
            StudentRepository studentRepository,
            StudentRegistrationValidator validator,
            PasswordHashPort passwordHashPort) {
        return new RegisterStudentService(studentRepository, validator, passwordHashPort);
    }

    @Bean
    public StudentOrderHistoryUseCase studentOrderHistoryUseCase(
            RechargeOrderRepository rechargeOrderRepository
    ) {
        return new GetStudentOrderHistoryService(rechargeOrderRepository);
    }

}
