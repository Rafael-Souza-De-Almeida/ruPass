package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.repositories;

import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.RechargeOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RechargeOrderSpringDataRepository extends JpaRepository<RechargeOrderEntity, UUID> {
}
