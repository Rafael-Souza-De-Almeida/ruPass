package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CancelExpiredOrderUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CancelExpiredOrderService implements CancelExpiredOrderUseCase {

    private final RechargeOrderRepository rechargeOrderRepository;

    @Override
    public void execute() {
        LocalDateTime twentyFourHoursAgo = LocalDateTime.now().minusHours(24);

        List<RechargeOrder> expiredOrders = rechargeOrderRepository.findPendingOrdersOlderThan(twentyFourHoursAgo);

        expiredOrders.forEach(rechargeOrder -> {
            rechargeOrder.markAsCancelled();
            rechargeOrderRepository.save(rechargeOrder);
        });
    }
}
