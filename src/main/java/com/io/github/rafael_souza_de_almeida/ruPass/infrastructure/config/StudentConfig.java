package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.config;

import com.io.github.rafael_souza_de_almeida.ruPass.application.ports.out.PasswordHashPort;
import com.io.github.rafael_souza_de_almeida.ruPass.application.services.*;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.*;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.StudentValidator;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.security.PasswordHashAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class StudentConfig {

    @Bean
    public StudentValidator studentRegistrationValidator(StudentRepository studentRepository) {
        return new StudentValidator(studentRepository);
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
    public GetStudentUseCase getStudentUseCase(StudentRepository studentRepository) {
        return new GetStudentService(studentRepository);
    }

    @Bean
    public RegisterStudentUseCase registerStudentUseCase(
            StudentRepository studentRepository,
            StudentValidator validator,
            PasswordHashPort passwordHashPort) {
        return new RegisterStudentService(studentRepository, validator, passwordHashPort);
    }

    @Bean
    public EditStudentUseCase editStudentUseCase(StudentRepository repository, PasswordHashPort passwordHasher, StudentValidator studentValidator) {
        return new EditStudentService(repository, passwordHasher, studentValidator);
    }

    @Bean
    public DeleteStudentUseCase deleteStudentUseCase(StudentRepository repository) {
        return new DeleteStudentService(repository);
    }

    @Bean
    public StudentOrderHistoryUseCase studentOrderHistoryUseCase(
            RechargeOrderRepository rechargeOrderRepository
    ) {
        return new GetStudentOrderHistoryService(rechargeOrderRepository);
    }

}
