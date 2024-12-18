package org.example.scheduler.schedule.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulePageDto {
    private Long scheduleId;
    private String title;
    private String contents;
    private String authorName; // Author 정보 (Optional)
    private Long commentCount; // 댓글 개수

}
