package com.io.github.rafael_souza_de_almeida.ruPass.domain.services;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.DuplicatedRegistrationException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.RegistrationNumber;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;

public class StudentValidator {

    private final StudentRepository studentRepository;

    public StudentValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void validateNewStudent(RegistrationNumber registrationNumber, Cpf cpf, Email email) {

        if(studentRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new DuplicatedRegistrationException("This registration number is already used.");
        }

        if(studentRepository.existsByCpf(cpf)) {
            throw new DuplicatedRegistrationException("CPF already registered.");
        }

        if(studentRepository.existsByEmail(email)) {
            throw new DuplicatedRegistrationException("Email already registered");
        }

    }

    public void validateExistingStudent(Email email) {
        if(studentRepository.existsByEmail(email)) {
            throw new DuplicatedRegistrationException("Email already registered");
        }
    }
}
