package com.io.github.rafael_souza_de_almeida.ruPass.domain.repository;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;
import java.util.UUID;

public interface RechargeOrderRepository {

    void save(RechargeOrder order);
    Page<RechargeOrder> findByIdOrderByCreatedAtDesc(UUID studentId, Pageable pageable);
    Optional<RechargeOrder> findById(UUID id);
}
