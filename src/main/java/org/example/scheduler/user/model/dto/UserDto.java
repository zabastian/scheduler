package org.example.scheduler.user.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.scheduler.common.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long userId;

    private String userName;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static UserDto convertDto(User user) {
        return new UserDto(user.getUserId(),
            user.getUserName(),
            user.getEmail(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
