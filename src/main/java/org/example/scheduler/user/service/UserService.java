package org.example.scheduler.user.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduler.common.config.encode.PasswordEncoder;
import org.example.scheduler.common.entity.User;
import org.example.scheduler.common.exception.LoginException;
import org.example.scheduler.common.exception.ValidateException;
import org.example.scheduler.user.model.dto.UserDto;
import org.example.scheduler.user.model.request.LoginRequest;
import org.example.scheduler.user.model.request.SignUpRequest;
import org.example.scheduler.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(SignUpRequest request) {
        String encodePassword = passwordEncoder.encode(request.password());
        boolean validate = userRepository.existsByEmail(request.email());
        if(validate) {
            throw new ValidateException("이미 존재하는 이메일입니다.", HttpStatus.CONFLICT);
        }
        User user = User.createUser(request,encodePassword);
        userRepository.save(user);
        return UserDto.convertDto(user);
    }

    public Long getUserId(LoginRequest request) {
        User user = userRepository.findByEmail(request.email());
        if (passwordEncoder.matches(request.password(), user.getPassword())) {
            return user.getUserId();
        } else {
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }
    }

}
