package org.example.scheduler.schedule.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduler.common.entity.Schedule;
import org.example.scheduler.common.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDto {

    private Long scheduleId;
    private String title;
    private String contents;
    private User author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ScheduleDto convertDto(Schedule schedule) {
        return new ScheduleDto(schedule.getScheduleId(),
            schedule.getTitle(),
            schedule.getContents(),
            schedule.getAuthor(),
            schedule.getCreatedAt(),
            schedule.getUpdatedAt()
        );
    }
}