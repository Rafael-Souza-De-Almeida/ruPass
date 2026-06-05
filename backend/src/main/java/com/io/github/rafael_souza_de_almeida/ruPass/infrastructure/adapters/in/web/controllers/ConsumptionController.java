package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.ConsumeTicketUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.ConsumptionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/consumptions")
@RequiredArgsConstructor
@Slf4j
public class ConsumptionController {

    private final ConsumeTicketUseCase consumeTicketUseCase;

    @PostMapping
    public ResponseEntity<Void> consumeTicket(@RequestBody @Validated ConsumptionRequest request) {

        log.info("request received from the machine: {}", request.turnstileId());

        consumeTicketUseCase.execute(request.studentId());

        return ResponseEntity.status(HttpStatus.OK).build();

    }


}
