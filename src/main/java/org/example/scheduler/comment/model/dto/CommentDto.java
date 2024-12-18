package org.example.scheduler.comment.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduler.common.entity.Comment;
import org.example.scheduler.schedule.model.dto.ScheduleDto;
import org.example.scheduler.user.model.dto.UserDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long commentId;
    private String contents;
    private UserDto user;
    private ScheduleDto schedule;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentDto convertDto(Comment comment) {
        return new CommentDto(comment.getCommentId(),
            comment.getContents(),
            UserDto.convertDto(comment.getUser()),
            ScheduleDto.convertDto(comment.getSchedule()),
            comment.getCreatedAt(),
            comment.getUpdatedAt()
            );
    }

}
