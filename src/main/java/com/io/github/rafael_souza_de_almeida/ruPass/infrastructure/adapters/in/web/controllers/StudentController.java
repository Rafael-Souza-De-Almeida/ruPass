package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.StudentRegistrationRequest;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.StudentRegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final RegisterStudentUseCase registerStudentUseCase;

    @PostMapping
    public ResponseEntity<StudentRegistrationResponse> registerStudent(@Valid @RequestBody StudentRegistrationRequest request) {

        RegisterStudentCommand studentCommand = new RegisterStudentCommand(
                request.fullName(),
                request.registrationNumber(),
                request.studentType(),
                request.cpf()
        );

        Student savedStudent = registerStudentUseCase.execute(studentCommand);

        StudentRegistrationResponse response = new StudentRegistrationResponse(
                savedStudent.getId(),
                savedStudent.getFullName(),
                savedStudent.getRegistrationNumber()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
