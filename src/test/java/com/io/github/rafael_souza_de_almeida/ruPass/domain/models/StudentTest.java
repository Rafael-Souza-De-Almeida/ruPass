package com.io.github.rafael_souza_de_almeida.ruPass.domain.models;

import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.StudentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    private final String VALID_CPF = "12345678909";

    @Test
    @DisplayName("Should create a valid student and initialize an empty wallet")
    void shouldCreateValidStudent() {
        String name = "João Silva";
        String registration = "20230001";
        StudentType type = StudentType.UNDERGRADUATE;

        Student student = new Student(name, registration, type, VALID_CPF);

        assertThat(student.getId()).isNotNull();
        assertThat(student.getFullName()).isEqualTo(name);
        assertThat(student.getRegistrationNumber()).isEqualTo(registration);
        assertThat(student.getStudentType()).isEqualTo(type);
        assertThat(student.getCpf().value()).isEqualTo(VALID_CPF);
        assertThat(student.getWallet()).isNotNull();
        assertThat(student.getWallet().getStudentId()).isEqualTo(student.getId());
    }

    @Test
    @DisplayName("Should register face biometrics correctly")
    void shouldRegisterFaceBiometrics() {
        Student student = new Student("João Silva", "20230001", StudentType.UNDERGRADUATE, VALID_CPF);
        String token = "some-biometric-token-123";

        student.registerFaceBiometrics(token);

        assertThat(student.getFaceBiometrics()).isNotNull();
        assertThat(student.getFaceBiometrics().getToken()).isEqualTo(token);
    }

    @Test
    @DisplayName("Should use the provided ID when using the full constructor")
    void shouldCreateStudentWithSpecificId() {
        UUID customId = UUID.randomUUID();
        Student student = new Student(customId, "Ana Souza", "20230002", StudentType.UNDERGRADUATE, null, null, null);

        assertThat(student.getId()).isEqualTo(customId);
    }

    @Test
    @DisplayName("Should throw exception when name is empty or only spaces")
    void shouldThrowExceptionWhenNameIsEmpty() {
        assertThatThrownBy(() -> new Student("", "12345", StudentType.UNDERGRADUATE, "12345678909"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name can not be blank.");

        assertThatThrownBy(() -> new Student("   ", "12345", StudentType.UNDERGRADUATE, "12345678909"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name can not be blank.");
    }

    @Test
    @DisplayName("Should throw exception when registration number is empty")
    void shouldThrowExceptionWhenRegistrationIsEmpty() {
        assertThatThrownBy(() -> new Student("Rafael Almeida", "", StudentType.UNDERGRADUATE, "12345678909"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Registration number can not be blank.");
    }

}