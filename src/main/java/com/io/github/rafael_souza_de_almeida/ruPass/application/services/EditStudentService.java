package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.ports.out.PasswordHashPort;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.EditStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.EditStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Password;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.StudentValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EditStudentService implements EditStudentUseCase {

    private final StudentRepository repository;
    private final PasswordHashPort passwordHasher;
    private final StudentValidator studentValidator;

    @Override
    public Student execute(EditStudentCommand command) {

        Student student = repository.findById(command.studentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Email emailObj = null;
        if (command.email() != null && !command.email().isBlank()) {
            emailObj = new Email(command.email());
            studentValidator.validateExistingStudent(emailObj);
        }

        Password passwordObj = null;
        if (command.password() != null && !command.password().isBlank()) {
            String hashed = passwordHasher.encode(command.password());
            passwordObj = new Password(hashed);
        }

        student.updateProfile(command.fullName(), emailObj, passwordObj, command.photoUrl());

        return repository.save(student);

    }
}
