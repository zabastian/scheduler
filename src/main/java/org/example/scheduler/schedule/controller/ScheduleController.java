package org.example.scheduler.schedule.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduler.schedule.model.dto.ScheduleDto;
import org.example.scheduler.schedule.model.request.CreateScheduleRequest;
import org.example.scheduler.schedule.model.request.UpdateScheduleRequest;
import org.example.scheduler.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long scheduleId) {
        return new ResponseEntity<>(scheduleService.getSchedule(scheduleId), HttpStatus.OK);
    }

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleDto> createSchedule(@Valid @RequestBody CreateScheduleRequest request, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        return new ResponseEntity<>(scheduleService.createSchedule(request,userId),HttpStatus.OK);
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long scheduleId,@Valid @RequestBody UpdateScheduleRequest request) {
        return new ResponseEntity<>(scheduleService.updateSchedule(scheduleId,request),HttpStatus.OK);
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
