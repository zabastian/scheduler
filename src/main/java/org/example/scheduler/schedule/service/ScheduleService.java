package org.example.scheduler.schedule.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.scheduler.common.entity.Schedule;
import org.example.scheduler.common.entity.User;
import org.example.scheduler.common.exception.ValidateException;
import org.example.scheduler.schedule.model.dto.ScheduleDto;
import org.example.scheduler.schedule.model.request.CreateScheduleRequest;
import org.example.scheduler.schedule.model.request.UpdateScheduleRequest;
import org.example.scheduler.schedule.repository.ScheduleRepository;
import org.example.scheduler.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleDto getSchedule(long scheduleId) {
        Schedule schedule = findScheduleById(scheduleId);
        return convertToDto(schedule);
    }

    public ScheduleDto createSchedule(CreateScheduleRequest request, long userId) {
        User author = userRepository.findById(userId).orElseThrow(()-> new ValidateException("존재 하지 않는 유저 입니다.", HttpStatus.NOT_FOUND));
        Schedule schedule = Schedule.of(request,author);
        scheduleRepository.save(schedule);
        return convertToDto(schedule);
    }

    public ScheduleDto updateSchedule(long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = findScheduleById(scheduleId);
        schedule.updateSchedule(request);
        scheduleRepository.save(schedule);
        return convertToDto(schedule);
    }

    public void deleteSchedule(long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }

    private Schedule findScheduleById(long scheduleId) {
        return scheduleRepository.findById(scheduleId)
            .orElseThrow(()-> new ValidateException("존재 하지 않는 게시글 입니다.", HttpStatus.NOT_FOUND));
    }

    private ScheduleDto convertToDto(Schedule schedule) {
        return ScheduleDto.convertDto(schedule);
    }

}
