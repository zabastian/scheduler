package org.example.scheduler.schedule.model.request;

import jakarta.validation.constraints.NotBlank;

public record CreateScheduleRequest(
    @NotBlank(message = "Title cannot be blank")
    String title,
    @NotBlank(message = "Content cannot be blank")
    String contents
) {

}
