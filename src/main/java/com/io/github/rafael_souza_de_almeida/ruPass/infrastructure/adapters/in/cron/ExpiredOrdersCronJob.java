package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.cron;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CancelExpiredOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpiredOrdersCronJob {

    private final CancelExpiredOrderUseCase cancelExpiredOrderUseCase;

    @Scheduled(cron = "0 0 * * * *")
    public void cleanupExpiredPixOrders() {
        cancelExpiredOrderUseCase.execute();
    }
}
