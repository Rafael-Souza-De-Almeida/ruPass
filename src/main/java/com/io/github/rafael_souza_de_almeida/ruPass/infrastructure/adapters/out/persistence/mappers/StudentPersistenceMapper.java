package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.mappers;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
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

        return new Student(
                studentEntity.getId(),
                studentEntity.getFullName(),
                studentEntity.getRegistrationNumber(),
                StudentType.valueOf(studentEntity.getStudentType()),
                walletDomain,
                cpfDomain,
                null
        );
    }


    public StudentEntity toEntity(Student domain) {
        if (domain == null) return null;

        StudentEntity entity = new StudentEntity();

        entity.setId(domain.getId());
        entity.setFullName(domain.getFullName());
        entity.setRegistrationNumber(domain.getRegistrationNumber());
        entity.setStudentType(domain.getStudentType().toString());
        entity.setCpf(domain.getCpf().value());


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