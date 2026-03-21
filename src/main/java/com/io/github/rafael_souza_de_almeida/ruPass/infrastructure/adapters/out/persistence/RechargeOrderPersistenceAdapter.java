package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.RechargeOrderEntity;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.mappers.RechargeOrderPersistenceMapper;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.repositories.RechargeOrderSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RechargeOrderPersistenceAdapter implements RechargeOrderRepository {

    private final RechargeOrderSpringDataRepository repository;

    @Override
    public void save(RechargeOrder order) {
        RechargeOrderEntity orderToSave = RechargeOrderPersistenceMapper.toEntity(order);
        repository.save(orderToSave);
    }

    @Override
    public Optional<RechargeOrder> findById(UUID id) {
        return repository.findById(id).map(RechargeOrderPersistenceMapper::toDomain);
    }

    @Override
    public Page<RechargeOrder> findByIdOrderByCreatedAtDesc(UUID studentId, Pageable pageable) {
        return repository.findByStudentIdOrderByCreatedAtDesc(studentId, pageable)
                .map(RechargeOrderPersistenceMapper::toDomain);
    }
}
