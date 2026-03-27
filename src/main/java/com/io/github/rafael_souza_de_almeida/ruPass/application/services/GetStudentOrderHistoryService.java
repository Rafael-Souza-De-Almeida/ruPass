package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.StudentOrderHistoryUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.RechargeOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@RequiredArgsConstructor
public class GetStudentOrderHistoryService implements StudentOrderHistoryUseCase {

    private final RechargeOrderRepository rechargeOrderRepository;

    @Override
    public Page<RechargeOrder> execute(UUID studentId, Pageable pageable) {

        return rechargeOrderRepository.findByIdOrderByCreatedAtDesc(studentId, pageable);


    }
}
