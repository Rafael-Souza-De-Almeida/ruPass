package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import java.util.UUID;

public class Wallet {

    private UUID id;
    private UUID studentId;
    private int balance;

    public Wallet(UUID studentId) {
        this.id = UUID.randomUUID();
        this.studentId = studentId;
        this.balance = 0;
    }

    public Wallet(UUID id, UUID studentId, int balance) {
        this.id = id;
        this.studentId = studentId;
        this.balance = balance;
    }

    public Wallet() {

    }


    public UUID getId() {
        return id;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public int getBalance() {
        return balance;
    }
}
