package org.example.scheduler.comment.model.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
    @NotBlank(message = "스케쥴 ID가 없습니다.")
    long scheduleId,
    @NotBlank(message = "댓글 내용이 없습니다.")
    String contents
) {

}
