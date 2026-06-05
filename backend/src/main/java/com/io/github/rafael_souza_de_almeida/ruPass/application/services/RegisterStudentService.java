package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.ports.out.PasswordHashPort;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.Course;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Password;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.RegistrationNumber;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.StudentValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterStudentService implements RegisterStudentUseCase {

    private final StudentRepository studentRepository;
    private final StudentValidator validator;
    private final PasswordHashPort passwordHasher;

    @Override
    public Student execute(RegisterStudentCommand command) {

        Cpf cpf = new Cpf(command.cpf());
        Email email = new Email(command.email());
        RegistrationNumber registrationNumber = new RegistrationNumber(command.registrationNumber());
        Password password = new Password(command.password());

        validator.validateNewStudent(registrationNumber, cpf, email);

        var passwordEncoded = new Password(passwordHasher.encode(password.value()));

        Student student = new Student(
                command.fullName(),
                email,
                passwordEncoded,
                registrationNumber,
                cpf,
                Course.valueOf(command.course())

        );

        return studentRepository.save(student);

    }
}
