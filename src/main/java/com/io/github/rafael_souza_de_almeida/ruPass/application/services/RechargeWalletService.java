package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RechargeWalletUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeWalletCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RechargeWalletService implements RechargeWalletUseCase {

    private final StudentRepository studentRepository;

    @Override
    public Wallet execute(RechargeWalletCommand command) {
        Student student = studentRepository.findById(command.studentId())
                .orElseThrow(() -> new StudentNotFoundException("Student Not found."));

        student.getWallet().addTickets(command.breakfastQuantity(), command.lunchDinnerQuantity());

        studentRepository.save(student);

        return student.getWallet();

    }
}
