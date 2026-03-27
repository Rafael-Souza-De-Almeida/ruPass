package com.io.github.rafael_souza_de_almeida.ruPass.application.usecases;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.EditStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;

public interface EditStudentUseCase {

    Student execute(EditStudentCommand command);

}
