package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.StudentRegistrationValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterStudentService implements RegisterStudentUseCase {

    private final StudentRepository studentRepository;
    private final StudentRegistrationValidator validator;

    @Override
    public Student execute(RegisterStudentCommand command) {

        Cpf cpf = new Cpf(command.cpf());

        validator.validateNewStudent(command.registrationNumber(), cpf);

        Student student = new Student(
                command.fullName(),
                command.email(),
                command.password(),
                command.registrationNumber(),
                command.studentType(),
                command.cpf()
        );

        return studentRepository.save(student);

    }
}
