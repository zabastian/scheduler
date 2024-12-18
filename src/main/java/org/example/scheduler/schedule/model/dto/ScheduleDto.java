package org.example.scheduler.schedule.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduler.common.entity.Schedule;
import org.example.scheduler.common.entity.User;
import org.example.scheduler.user.model.dto.UserDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private Long scheduleId;
    private String title;
    private String contents;
    private UserDto author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // TODO :: 정적 팩토리 메서드를 사용하면 어떤 장점이 있을까요?
    public static ScheduleDto convertDto(Schedule schedule) {
        return new ScheduleDto(schedule.getScheduleId(),
            schedule.getTitle(),
            schedule.getContents(),
            UserDto.convertDto(schedule.getAuthor()),
            schedule.getCreatedAt(),
            schedule.getUpdatedAt()
        );
    }
}