package com.io.github.rafael_souza_de_almeida.ruPass.domain.repository;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {

    Student save(Student student);
    Optional<Student> findById(UUID id);

}
