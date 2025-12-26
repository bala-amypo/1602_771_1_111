// AuthController.java
package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final Map<String, AuthRequest> users = new HashMap<>();

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req) {
        if (users.containsKey(req.getUsername())) {
            return ResponseEntity.badRequest().build();
        }
        users.put(req.getUsername(), req);
        String token = jwtUtil.generateToken(
                req.getUsername(),
                req.getRole(),
                req.getEmail(),
                "1"
        );
        return ResponseEntity.ok(new AuthResponse(token, "1", req.getEmail(), req.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        AuthRequest stored = users.get(req.getUsername());
        if (stored == null || !stored.getPassword().equals(req.getPassword())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtUtil.generateToken(
                stored.getUsername(),
                stored.getRole(),
                stored.getEmail(),
                "1"
        );
        return ResponseEntity.ok(new AuthResponse(token, "1", stored.getEmail(), stored.getRole()));
    }
}
