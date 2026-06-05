package com.io.github.rafael_souza_de_almeida.ruPass.domain.repository;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.RegistrationNumber;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {

    Student save(Student student);
    Optional<Student> findById(UUID id);
    Optional<Student> findByEmail(Email email);
    void delete(UUID id);
    boolean existsByRegistrationNumber(RegistrationNumber registrationNumber);
    boolean existsByCpf(Cpf cpf);
    boolean existsByEmail(Email email);


}
