package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CreateRechargeOrderUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeOrderCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
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
    private final CreateRechargeOrderUseCase createRechargeOrderUseCase;

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

    @PostMapping("/{id}/orders")
    public ResponseEntity<RechargeWalletResponse> createRechargeOrder(@PathVariable("id") UUID studentId,
                                                                 @Valid @RequestBody RechargeWalletRequest request) {

        int breakfast = request.breakfastAmount() != null ? request.breakfastAmount() : 0;
        int lunch = request.lunchDinnerAmount() != null ? request.lunchDinnerAmount() : 0;

        RechargeOrderCommand rechargeOrderCommand = new RechargeOrderCommand(studentId, breakfast, lunch);

        RechargeOrder order = createRechargeOrderUseCase.execute(rechargeOrderCommand);

        RechargeWalletResponse response = new RechargeWalletResponse(
                order.getId(),
                order.getTotalAmount(),
                order.getOrderStatus().toString(),
                "Order created sucessfully. Waiting for payment.");

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

}
