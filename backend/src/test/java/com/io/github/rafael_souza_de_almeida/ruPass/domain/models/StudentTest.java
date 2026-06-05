package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.EmptyRegistrationNumberException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Cpf;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Email;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.Password;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.valueobjects.RegistrationNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    private final Cpf VALID_CPF = new Cpf("12345678909");
    private final Email VALID_EMAIL = new Email("test@gmail.com");
    private final Password VALID_PASSWORD = new Password("12345678");
    private final RegistrationNumber VALID_REGISTRATION = new RegistrationNumber("202200057689");

    @Test
    @DisplayName("Should create a valid student and initialize an empty wallet")
    void shouldCreateValidStudent() {
        String name = "João Silva";
        StudentType type = StudentType.UNDERGRADUATE;

        Student student = new Student(name, VALID_EMAIL, VALID_PASSWORD, VALID_REGISTRATION, type, VALID_CPF);

        assertThat(student.getId()).isNotNull();
        assertThat(student.getFullName()).isEqualTo(name);
        assertThat(student.getEmail()).isEqualTo(VALID_EMAIL);
        assertThat(student.getPassword()).isEqualTo(VALID_PASSWORD);
        assertThat(student.getRegistrationNumber()).isEqualTo(VALID_REGISTRATION);
        assertThat(student.getStudentType()).isEqualTo(type);
        assertThat(student.getCpf().value()).isEqualTo(VALID_CPF.value());
        assertThat(student.getWallet()).isNotNull();
        assertThat(student.getWallet().getStudentId()).isEqualTo(student.getId());
    }

    @Test
    @DisplayName("Should register face biometrics correctly")
    void shouldRegisterFaceBiometrics() {
        Student student = new Student("João Silva", VALID_EMAIL, VALID_PASSWORD, VALID_REGISTRATION, StudentType.UNDERGRADUATE, VALID_CPF);
        String token = "some-biometric-token-123";

        student.registerFaceBiometrics(token);

        assertThat(student.getFaceBiometrics()).isNotNull();
        assertThat(student.getFaceBiometrics().getToken()).isEqualTo(token);
    }

    @Test
    @DisplayName("Should use the provided ID when using the full constructor")
    void shouldCreateStudentWithSpecificId() {
        UUID customId = UUID.randomUUID();
        Student student = new Student(customId, "Ana Souza", VALID_EMAIL, VALID_PASSWORD, VALID_REGISTRATION, StudentType.UNDERGRADUATE, null, null, null, null);

        assertThat(student.getId()).isEqualTo(customId);
    }

    @Test
    @DisplayName("Should throw exception when name is empty or only spaces")
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThatThrownBy(() -> new Student("", VALID_EMAIL, VALID_PASSWORD, VALID_REGISTRATION, StudentType.UNDERGRADUATE, VALID_CPF))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name cannot be blank.");

        assertThatThrownBy(() -> new Student("   ", VALID_EMAIL, VALID_PASSWORD, VALID_REGISTRATION, StudentType.UNDERGRADUATE, VALID_CPF))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name cannot be blank.");
    }

    @Test
    @DisplayName("Should throw exception when registration number is empty")
    void shouldThrowExceptionWhenRegistrationIsEmpty() {

        assertThatThrownBy(() -> new RegistrationNumber(""))
                .isInstanceOf(EmptyRegistrationNumberException.class)
                .hasMessageContaining("Registration number cannot be empty.");
    }

}