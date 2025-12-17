package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public void register(@RequestBody AppUser user) {
        userRepository.save(
                new AppUser(user.getUsername(), encoder.encode(user.getPassword()))
        );
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AppUser user) {
        AppUser dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow();

        if (!encoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(dbUser.getUsername());
        return Map.of("token", token);
    }
}
