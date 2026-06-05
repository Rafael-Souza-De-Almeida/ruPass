package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.config;

import com.io.github.rafael_souza_de_almeida.ruPass.application.services.ConsumeTicketService;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.ConsumeTicketUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumptionConfig {

    @Bean
    public ConsumeTicketUseCase consumeTicketUseCase(StudentRepository studentRepository){
        return new ConsumeTicketService(studentRepository);
    }
}
