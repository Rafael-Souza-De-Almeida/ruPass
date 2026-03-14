package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.FaceBiometrics;

import java.util.Objects;
import java.util.UUID;

public class Student {

    private UUID id;
    private String fullName;
    private String registrationNumber;
    private StudentType studentType;
    private Wallet wallet;
    private Cpf cpf;
    private FaceBiometrics faceBiometrics;

    public Student(String fullName, String registrationNumber, StudentType studentType, String cpfRawString) {

        Objects.requireNonNull(fullName, "Name can not be blank.");
        Objects.requireNonNull(registrationNumber, "Registration number can not be blank.");

        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.registrationNumber = registrationNumber;
        this.studentType = studentType;
        this.cpf = new Cpf(cpfRawString);
        this.wallet = new Wallet(this.id);
    }

    public Student(UUID id, String fullName, String registrationNumber, StudentType studentType, Wallet wallet, Cpf cpf, FaceBiometrics faceBiometrics) {
        this.id = id;
        this.fullName = fullName;
        this.registrationNumber = registrationNumber;
        this.studentType = studentType;
        this.wallet = wallet;
        this.cpf = cpf;
        this.faceBiometrics = faceBiometrics;
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

    public String getRegistrationNumber() {
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
}
