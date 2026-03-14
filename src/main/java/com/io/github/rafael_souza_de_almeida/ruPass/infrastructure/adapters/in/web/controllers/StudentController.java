package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RechargeWalletUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeWalletCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Wallet;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.RechargeWalletRequest;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.RechargeWalletResponse;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.StudentRegistrationRequest;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.StudentRegistrationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final RegisterStudentUseCase registerStudentUseCase;
    private final RechargeWalletUseCase rechargeWalletUseCase;

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

    @PostMapping("/{id}/wallet/recharge")
    public ResponseEntity<RechargeWalletResponse> rechargeWallet(@PathVariable("id") UUID studentId,
                                                                 @Valid @RequestBody RechargeWalletRequest request) {

        RechargeWalletCommand rechargeWalletCommand = new RechargeWalletCommand(studentId, request.amount());

        Wallet rechargedWallet = rechargeWalletUseCase.execute(rechargeWalletCommand);

        RechargeWalletResponse response = new RechargeWalletResponse(rechargedWallet.getBalance());

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

}
