package com.example.JWTSecurity.controller;

import com.example.JWTSecurity.dto.LoginRequestDTO;
import com.example.JWTSecurity.dto.LoginResponseDTO;
import com.example.JWTSecurity.dto.UserDTO;
import com.example.JWTSecurity.entity.User;
import com.example.JWTSecurity.repository.UserRepository;
import com.example.JWTSecurity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService userService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO requestDTO) {
        return userService.login(requestDTO);
    }

    @GetMapping("/me")
    public UserDTO getMe(Authentication auth) {
        String email = auth.getName(); // from JwtAuthFilter
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDTO(user.getId(), user.getEmail(), user.getRole());
    }
}
