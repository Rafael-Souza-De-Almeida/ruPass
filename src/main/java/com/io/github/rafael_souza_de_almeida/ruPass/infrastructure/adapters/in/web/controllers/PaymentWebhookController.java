package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.ProcessPaymentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.ProcessPaymentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.PixConfirmationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/webhooks/pix")
@RequiredArgsConstructor
@Slf4j
public class PaymentWebhookController {

    private final ProcessPaymentUseCase processPaymentUseCase;

    @PostMapping
    public ResponseEntity<Void> receivePixConfirmation(@RequestBody PixConfirmationRequest request) {

        ProcessPaymentCommand command = new ProcessPaymentCommand(request.orderId(), request.status(), request.transactionId());

        processPaymentUseCase.execute(command);

        return ResponseEntity.status(HttpStatus.OK).build();

    }


}

