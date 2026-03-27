package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.repositories;

import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface
StudentSpringDataRepository extends JpaRepository<StudentEntity, UUID> {

    Optional<StudentEntity> findByRegistrationNumber(String registrationNumber);
    Optional<StudentEntity> findByCpf(String cpf);
    Optional<StudentEntity> findByEmail(String email);

    boolean existsByRegistrationNumber(String registrationNumber);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
