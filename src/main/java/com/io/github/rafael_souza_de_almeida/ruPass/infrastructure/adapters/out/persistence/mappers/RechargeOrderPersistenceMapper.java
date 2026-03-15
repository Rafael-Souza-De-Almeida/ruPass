package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.mappers;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.RechargeOrderEntity;

public class RechargeOrderPersistenceMapper {

    public static RechargeOrderEntity toEntity(RechargeOrder domain) {
        return new RechargeOrderEntity(
                domain.getId(),
                domain.getStudentId(),
                domain.getBreakfastQuantity(),
                domain.getLunchDinnerQuantity(),
                domain.getTotalAmount(),
                domain.getOrderStatus(),
                domain.getTransactionId(),
                domain.getCreatedAt()
        );
    }

    public static RechargeOrder toDomain(RechargeOrderEntity entity) {
        return new RechargeOrder(
                entity.getId(),
                entity.getStudentId(),
                entity.getBreakfastQuantity(),
                entity.getLunchDinnerQuantity(),
                entity.getTotalAmount(),
                entity.getOrderStatus(),
                entity.getTransactionId(),
                entity.getCreatedAt()
        );
    }

}
