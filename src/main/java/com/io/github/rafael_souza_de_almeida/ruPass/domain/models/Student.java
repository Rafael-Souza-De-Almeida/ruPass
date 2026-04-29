package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.Course;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.Role;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.*;

import java.util.UUID;

public class Student {

    private UUID id;
    private String fullName;
    private Email email;
    private Password password;
    private RegistrationNumber registrationNumber;
    private Wallet wallet;
    private Cpf cpf;
    private String photoUrl;
    private Role role;
    private Course course;

    public Student(String fullName, Email email, Password password, RegistrationNumber registrationNumber, Cpf cpf, Course course) {

        if(fullName.isBlank() || fullName.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }

        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.registrationNumber = registrationNumber;
        this.cpf = cpf;
        this.wallet = new Wallet(this.id);
        this.role = Role.ROLE_STUDENT;
        this.course = course;
    }

    public Student(UUID id, String fullName, Email email, Password password, RegistrationNumber registrationNumber, Wallet wallet, Cpf cpf, String photoUrl, Role role, Course course) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.registrationNumber = registrationNumber;
        this.wallet = wallet;
        this.cpf = cpf;
        this.photoUrl = photoUrl;
        this.role = role;
        this.course = course;
    }

    public Student() {
    }

    public void updateProfile(String newFullName, Email newEmail, Password newPassword, String newPhotoUrl) {

        if(newFullName != null && !newFullName.isBlank()) {
            this.fullName = newFullName;
        }

        if (newEmail != null) {
            this.email = newEmail;
        }

        if (newPassword != null) {
            this.password = newPassword;
        } //To do - Implement Email and password verification

        if(newPhotoUrl != null) {
            this.photoUrl = newPhotoUrl;
        }


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

    public Wallet getWallet() {
        return wallet;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public Course getCourse() {
        return course;
    }

    public Role getRole() {
        return role;
    }
}
