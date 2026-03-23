package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.Role;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.*;

import java.util.Objects;
import java.util.UUID;

public class Student {

    private UUID id;
    private String fullName;
    private Email email;
    private Password password;
    private RegistrationNumber registrationNumber;
    private StudentType studentType;
    private Wallet wallet;
    private Cpf cpf;
    private FaceBiometrics faceBiometrics;
    private Role role;

    public Student(String fullName, String emailRawString, String passwordRawString, String registrationNumberRawString, StudentType studentType, String cpfRawString) {

        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = new Email(emailRawString);
        this.password = new Password(passwordRawString);
        this.registrationNumber = new RegistrationNumber(registrationNumberRawString);
        this.studentType = studentType;
        this.cpf = new Cpf(cpfRawString);
        this.wallet = new Wallet(this.id);
        this.role = Role.ROLE_STUDENT;
    }

    public Student(UUID id, String fullName, Email email, Password password, RegistrationNumber registrationNumber, StudentType studentType, Wallet wallet, Cpf cpf, FaceBiometrics faceBiometrics, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.registrationNumber = registrationNumber;
        this.studentType = studentType;
        this.wallet = wallet;
        this.cpf = cpf;
        this.faceBiometrics = faceBiometrics;
        this.role = role;
    }

    public void registerFaceBiometrics(String token) {
        this.faceBiometrics = new FaceBiometrics(token);
    }


    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public RegistrationNumber getRegistrationNumber() {
        return registrationNumber;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public FaceBiometrics getFaceBiometrics() {
        return faceBiometrics;
    }

    public Role getRole() {
        return role;
    }
}
