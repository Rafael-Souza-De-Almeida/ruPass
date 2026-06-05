package com.io.github.rafael_souza_de_almeida.ruPass.application.services;

import com.io.github.rafael_souza_de_almeida.ruPass.application.usecases.ConsumeTicketUseCase;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.exceptions.StudentNotFoundException;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.Student;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.models.enums.TicketCategory;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.repository.StudentRepository;
import com.io.github.rafael_souza_de_almeida.ruPass.domain.services.CafeteriaSchedule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class ConsumeTicketService implements ConsumeTicketUseCase {

    private final StudentRepository studentRepository;

    @Override
    public void execute(UUID studentId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found."));

        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();

        LocalTime now = LocalTime.now();

        TicketCategory category = CafeteriaSchedule.determineMealByTime(now, currentDay);

        student.getWallet().consumeTicket(category);

        studentRepository.save(student);

        log.info("Access granted for student: {}", studentId);

    }
}
