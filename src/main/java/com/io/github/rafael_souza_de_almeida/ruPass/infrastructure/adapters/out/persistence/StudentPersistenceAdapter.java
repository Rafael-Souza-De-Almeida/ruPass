package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.StudentEntity;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.mappers.StudentPersistenceMapper;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.repositories.StudentSpringDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class StudentPersistenceAdapter implements StudentRepository {

    private final StudentSpringDataRepository repository;
    private final StudentPersistenceMapper mapper;

    @Override
    public Student save(Student student) {

        StudentEntity studentToSave = mapper.toEntity(student);

        if(studentToSave.getWallet() != null) {
            studentToSave.getWallet().setStudentEntity(studentToSave);
        }

        StudentEntity savedStudent = repository.save(studentToSave);


        return mapper.toDomain(savedStudent);

    }

    @Override
    public Optional<Student> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsByRegistrationNumber(String registrationNumber) {
        return repository.existsByRegistrationNumber(registrationNumber);
    }

    @Override
    public boolean existsByCpf(Cpf cpf) {
        return repository.existsByCpf(cpf.value());
    }
}
