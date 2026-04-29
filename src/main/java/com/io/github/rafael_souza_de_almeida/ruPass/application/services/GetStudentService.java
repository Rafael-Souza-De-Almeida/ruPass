package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.GetStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class GetStudentService implements GetStudentUseCase {

    private final StudentRepository studentRepository;

    @Override
    public Student execute(UUID id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
    }
}
