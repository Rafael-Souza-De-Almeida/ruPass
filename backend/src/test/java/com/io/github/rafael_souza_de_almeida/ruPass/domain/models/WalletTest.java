package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WalletTest {

    @Test
    @DisplayName("Should create a new wallet correctly")
    void shouldCreateANewWalletSuccessfully() {

        UUID studentId = UUID.randomUUID();

        Wallet wallet = new Wallet(studentId);

        assertThat(wallet.getStudentId()).isEqualTo(studentId);
        assertThat(wallet.getBreakfastBalance()).isEqualTo(0);
        assertThat(wallet.getLunchDinnerBalance()).isEqualTo(0);

    }

    @Test
    @DisplayName("Should add the correct tickets quantity to the wallet")
    void ShouldAddTicketsToTheWalletSuccessfully() {

        Wallet wallet = new Wallet(UUID.randomUUID());

        wallet.addTickets(5, 2);
        wallet.addTickets(5, 2);

        assertThat(wallet.getLunchDinnerBalance()).isEqualTo(4);
        assertThat(wallet.getBreakfastBalance()).isEqualTo(10);

    }

    @Test
    @DisplayName("Should throw exception when breakfast amount is negative.")
    void shouldThrowExceptionWhenBreakfastAmountIsNegative() {

        Wallet wallet = new Wallet(UUID.randomUUID());

        assertThatThrownBy(() -> wallet.addTickets(-1, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Quantities cannot be negative.");

    }

    @Test
    @DisplayName("Should throw exception when breakfast amount is negative.")
    void shouldThrowExceptionWhenLunchDinnerAmountIsNegative() {

        Wallet wallet = new Wallet(UUID.randomUUID());

        assertThatThrownBy(() -> wallet.addTickets(5, -2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Quantities cannot be negative.");

    }
}
