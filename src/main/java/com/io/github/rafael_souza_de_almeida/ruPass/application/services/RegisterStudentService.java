package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.dto.RegisterStudentDto;
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
    public Student execute(RegisterStudentDto dto) {

        Cpf cpf = new Cpf(dto.cpf());

        validator.validateNewStudent(dto.registrationNumber(), cpf);

        Student student = new Student(
                dto.fullName(),
                dto.registrationNumber(),
                dto.studentType(),
                dto.cpf()
        );

        return studentRepository.save(student);

    }
}
