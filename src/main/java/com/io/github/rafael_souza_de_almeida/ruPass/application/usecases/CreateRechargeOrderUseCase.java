package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeOrderCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;

public interface CreateRechargeOrderUseCase {

    RechargeOrder execute(RechargeOrderCommand command);
}
