package org.example.scheduler.user.model.request;

import jakarta.validation.constraints.*;

public record SignUpRequest(
    @NotBlank
    @Size(max = 4)
    String userName,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영문 대소문자와 숫자로만 이루어진 문자열을 의미하며, 공백이나 특수문자는 허용되지 않습니다.")
    String password
) {

}
