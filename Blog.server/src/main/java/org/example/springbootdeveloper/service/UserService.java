package org.example.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.config.jwt.TokenProvider;
import org.example.springbootdeveloper.domain.User;
import org.example.springbootdeveloper.dto.AddUserRequest;
import org.example.springbootdeveloper.dto.CreateAccessTokenResponse;
import org.example.springbootdeveloper.dto.LoginRequest;
import org.example.springbootdeveloper.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) // 패스워드 암호화
                .build()).getId();
    }

    public CreateAccessTokenResponse findByEmailPassword(LoginRequest dto) {
        Optional<User> user = userRepository.findByEmail(dto.getEmail());
        user.orElseThrow(() -> new UsernameNotFoundException("User not fount"));

        if (!bCryptPasswordEncoder.matches(dto.getPassword(), user.get().getPassword())) {
            throw new BadCredentialsException("Password is incorrect");
        }

        return new CreateAccessTokenResponse(
                tokenProvider.generateToken(user.get(), Duration.ofHours(2))
        );
    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
