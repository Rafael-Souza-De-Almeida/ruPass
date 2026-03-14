package com.io.github.rafael_souza_de_almeida.ruPass.domain.services;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.DuplicatedRegistrationException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;

public class StudentRegistrationValidator {

    private final StudentRepository studentRepository;

    public StudentRegistrationValidator(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void validateNewStudent(String registrationNumber, Cpf cpf) {

        if(studentRepository.existsByRegistrationNumber(registrationNumber)) {
            throw new DuplicatedRegistrationException("This registration number is already used.");
        }

        if(studentRepository.existsByCpf(cpf)) {
            throw new DuplicatedRegistrationException("CPF already registered.");
        }

    }
}
