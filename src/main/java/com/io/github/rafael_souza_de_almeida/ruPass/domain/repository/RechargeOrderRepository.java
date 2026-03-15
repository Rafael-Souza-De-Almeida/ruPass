package com.io.github.rafael_souza_de_almeida.ruPass.domain.repository;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;

import java.util.Optional;
import java.util.UUID;

public interface RechargeOrderRepository {

    void save(RechargeOrder order);
    Optional<RechargeOrder> findById(UUID id);
}
