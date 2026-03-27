package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentEntity {

    @Id
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber;

    @Column(name = "student_type", nullable = false)
    private String studentType;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String role;

    @OneToOne(mappedBy = "studentEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private WalletEntity wallet;

}
