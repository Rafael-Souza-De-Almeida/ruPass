package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.dto.RegisterStudentDto;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;

public interface RegisterStudentUseCase {

    Student execute(RegisterStudentDto dto);
}
