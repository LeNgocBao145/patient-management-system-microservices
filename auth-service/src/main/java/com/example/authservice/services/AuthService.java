package com.example.authservice.services;

import com.example.authservice.dto.LoginRequestDTO;
import com.example.authservice.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordDecoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserService userService, PasswordEncoder passwordDecoder, JwtUtil jwtUtil){
        this.userService = userService;
        this.passwordDecoder = passwordDecoder;
        this.jwtUtil = jwtUtil;
    }

    public Optional<String> authenticate(LoginRequestDTO loginRequestDTO) {
        return userService.findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordDecoder.matches(loginRequestDTO.getPassword(), u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(), u.getRole()));
    }

    public boolean validateToken(String token){
        try{
            jwtUtil.validateToken(token);
            return true;
        }catch(JwtException e){
            return false;
        }
    }
}
