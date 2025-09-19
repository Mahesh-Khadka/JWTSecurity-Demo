package com.example.JWTSecurity.service;

// ✅ correct entity
import com.example.JWTSecurity.dto.LoginRequestDTO;
import com.example.JWTSecurity.dto.LoginResponseDTO;
import com.example.JWTSecurity.dto.UserDTO;
import com.example.JWTSecurity.entity.User;
import com.example.JWTSecurity.repository.UserRepository;
import com.example.JWTSecurity.security.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;
    public AuthService( UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.passwordEncoder  = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.modelMapper =  modelMapper;
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        if (!passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new LoginResponseDTO(token, userDTO);
    }
}
