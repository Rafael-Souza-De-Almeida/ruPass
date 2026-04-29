package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;

import java.util.UUID;

public interface GetStudentUseCase {
    Student execute(UUID id);
}
