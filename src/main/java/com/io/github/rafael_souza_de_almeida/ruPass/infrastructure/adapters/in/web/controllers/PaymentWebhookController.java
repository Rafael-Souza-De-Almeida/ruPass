package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.PixConfirmationRequest;
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
@Slf4j
public class PaymentWebhookController {

    @PostMapping
    public ResponseEntity<Void> receivePixConfirmation(@RequestBody PixConfirmationRequest request) {

        log.info("The order was paid!");
        log.info("Order ID: {}", request.orderId());
        log.info("Status: {}", request.status());

        return ResponseEntity.status(HttpStatus.OK).build();

    }


}

