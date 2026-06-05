package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.mappers;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.Course;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.Role;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Password;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.RegistrationNumber;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.StudentEntity;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentPersistenceMapper {

    public Student toDomain(StudentEntity studentEntity) {
        if (studentEntity == null) return null;


        Wallet walletDomain = null;

        if (studentEntity.getWallet() != null) {
            walletDomain = new Wallet(
                    studentEntity.getWallet().getId(),
                    studentEntity.getId(),
                    studentEntity.getWallet().getBreakfastBalance(),
                    studentEntity.getWallet().getLunchDinnerBalance()
            );
        }


        Cpf cpfDomain = new Cpf(studentEntity.getCpf());
        Email emailDomain = new Email(studentEntity.getEmail());
        Password passwordDomain = new Password(studentEntity.getPassword());
        RegistrationNumber registrationNumberDomain = new RegistrationNumber(studentEntity.getRegistrationNumber());


        return new Student(
                studentEntity.getId(),
                studentEntity.getFullName(),
                emailDomain,
                passwordDomain,
                registrationNumberDomain,
                walletDomain,
                cpfDomain,
                studentEntity.getPhotoUrl(),
                Role.valueOf(studentEntity.getRole()),
                Course.valueOf(studentEntity.getCourse())
        );
    }


    public StudentEntity toEntity(Student domain) {
        if (domain == null) return null;

        StudentEntity entity = new StudentEntity();

        entity.setId(domain.getId());
        entity.setFullName(domain.getFullName());
        entity.setPassword(domain.getPassword().value());
        entity.setEmail(domain.getEmail().value());
        entity.setRegistrationNumber(domain.getRegistrationNumber().value());
        entity.setCourse(domain.getCourse().name());
        entity.setCpf(domain.getCpf().value());
        entity.setPhotoUrl(domain.getPhotoUrl());
        entity.setRole(domain.getRole().toString());



        if (domain.getWallet() != null) {
            WalletEntity walletEntity = new WalletEntity();
            walletEntity.setId(domain.getWallet().getId());
            walletEntity.setBreakfastBalance(domain.getWallet().getBreakfastBalance());
            walletEntity.setLunchDinnerBalance(domain.getWallet().getLunchDinnerBalance());

            walletEntity.setStudentEntity(entity);

            entity.setWallet(walletEntity);
        }

        return entity;
    }
}