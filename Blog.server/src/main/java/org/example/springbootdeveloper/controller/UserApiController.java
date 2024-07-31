package org.example.springbootdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.AddUserRequest;
import org.example.springbootdeveloper.dto.CreateAccessTokenResponse;
import org.example.springbootdeveloper.dto.LoginRequest;
import org.example.springbootdeveloper.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @PostMapping("api/user")
    public ResponseEntity<Void> signUp(@RequestBody AddUserRequest request) {
        userService.save(request); // 회원가입 메서드 호출
        return ResponseEntity.ok()
                .build();

//        return "redirect:/login"; // 메서드의 리턴값을 String으로 설정하고 이 코드를 작성하면 회원가입이 완료된 이후에 로그인 페이지로 이동할 수 있다.
    }

    @PostMapping("/api/login")
    public ResponseEntity<CreateAccessTokenResponse> login(@RequestBody LoginRequest request) {
        CreateAccessTokenResponse response = userService.findByEmailPassword(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("api/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return ResponseEntity.ok()
                .build();
    }
}
