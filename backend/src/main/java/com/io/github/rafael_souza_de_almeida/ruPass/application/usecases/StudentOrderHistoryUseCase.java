package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface StudentOrderHistoryUseCase {

    Page<RechargeOrder> execute(UUID studentId, Pageable pageable);

}
