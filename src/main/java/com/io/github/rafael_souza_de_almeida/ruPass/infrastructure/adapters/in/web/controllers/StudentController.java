package com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.controllers;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.CreateRechargeOrderUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.RegisterStudentUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.StudentOrderHistoryUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RechargeOrderCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.command.RegisterStudentCommand;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.RechargeOrder;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.infrastructure.adapters.in.web.dto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final CreateRechargeOrderUseCase createRechargeOrderUseCase;
    private final StudentOrderHistoryUseCase studentOrderHistoryUseCase;

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
                "Order created successfully. Waiting for payment.");

        return ResponseEntity.status(HttpStatus.OK).body(response);


    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<Page<StudentOrderHistoryResponse>> getOrderHistory(@PathVariable UUID id,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<RechargeOrder> orders = studentOrderHistoryUseCase.execute(id, pageable);

        Page<StudentOrderHistoryResponse> response = orders.map(order ->
                new StudentOrderHistoryResponse(
                        order.getId(),
                        order.getCreatedAt(),
                        order.getBreakfastQuantity(),
                        order.getLunchDinnerQuantity(),
                        order.getTotalAmount(),
                        order.getOrderStatus(),
                        order.getTransactionId()
                ));

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

}
