package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.ProcessPaymentCommand;

public interface ProcessPaymentUseCase {

    void execute(ProcessPaymentCommand command);

}
