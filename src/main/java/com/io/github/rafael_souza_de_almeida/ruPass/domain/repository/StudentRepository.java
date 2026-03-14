package com.io.github.rafael_souza_de_almeida.ruPass.domain.repository;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {

    Student save(Student student);
    Optional<Student> findById(UUID id);
    boolean existsByRegistrationNumber(String registrationNumber);
    boolean existsByCpf(Cpf cpf);

}
