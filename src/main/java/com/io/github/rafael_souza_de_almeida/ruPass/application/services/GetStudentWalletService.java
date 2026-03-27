package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.GetStudentWalletUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetStudentWalletService implements GetStudentWalletUseCase {

    private final StudentRepository studentRepository;

    @Override
    public Wallet execute(UUID studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student not found."));

        return student.getWallet();

    }
}
