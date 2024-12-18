package org.example.scheduler.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduler.user.model.request.SignUpRequest;

@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String userName;

    private String email;

    private String password;

    public static User createUser(SignUpRequest request, String encodePassword) {
        return new User(null, request.userName(), request.email(), encodePassword);
    }
}
