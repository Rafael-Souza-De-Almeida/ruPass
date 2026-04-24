package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.DeleteStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteStudentService implements DeleteStudentUseCase {

    private final StudentRepository studentRepository;

    @Override
    public void execute(UUID id) {
        studentRepository.delete(id);
    }
}
