package org.example.scheduler.schedule.model.request;

import jakarta.validation.constraints.NotBlank;

public record CreateScheduleRequest(
    // TODO :: Record 가 뭘까요? 이걸 왜 사용하는걸까요? 우리 자바 17 버전을 사용하고 있으니 최신 자바 트랜트 따라가자!
    @NotBlank(message = "Title cannot be blank")
    String title,
    @NotBlank(message = "Content cannot be blank")
    String contents
) {

}
