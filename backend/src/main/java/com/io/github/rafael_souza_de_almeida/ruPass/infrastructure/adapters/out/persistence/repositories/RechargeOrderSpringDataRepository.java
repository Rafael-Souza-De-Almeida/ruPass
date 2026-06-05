package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.repositories;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.OrderStatus;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.RechargeOrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface RechargeOrderSpringDataRepository extends JpaRepository<RechargeOrderEntity, UUID> {
    Page<RechargeOrderEntity> findByStudentIdOrderByCreatedAtDesc(UUID studentId, Pageable pageable);
    List<RechargeOrderEntity> findByOrderStatusAndCreatedAtBefore(OrderStatus status, LocalDateTime timeLimit);
}
