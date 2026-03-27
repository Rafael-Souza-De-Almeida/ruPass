package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.SignInRequest;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.SignUpRequest;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.StudentRegistrationResponse;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.out.security.JwtServiceAdapter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtServiceAdapter jwtServiceAdapter;
    private final AuthenticationManager authenticationManager;
    private final RegisterStudentUseCase registerStudentUseCase;

    @PostMapping("/sign_in")
    public ResponseEntity<String> sign_in(@RequestBody SignInRequest request) {

        var authToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authenticatedUser = authenticationManager.authenticate(authToken);

        return ResponseEntity.status(HttpStatus.OK).body(jwtServiceAdapter.generateToken(authenticatedUser));

    }

    @PostMapping("/sign_up")
    public ResponseEntity<StudentRegistrationResponse> registerStudent(@Valid @RequestBody SignUpRequest request) {

        RegisterStudentCommand studentCommand = new RegisterStudentCommand(
                request.fullName(),
                request.email(),
                request.password(),
                request.registrationNumber(),
                request.studentType(),
                request.cpf()
        );

        Student savedStudent = registerStudentUseCase.execute(studentCommand);

        StudentRegistrationResponse response = new StudentRegistrationResponse(
                savedStudent.getId(),
                savedStudent.getFullName(),
                savedStudent.getRegistrationNumber().value()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
