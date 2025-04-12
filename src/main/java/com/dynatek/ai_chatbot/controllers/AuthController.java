package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.AuthRequest;
import com.dynatek.ai_chatbot.models.AuthResponse;
import com.dynatek.ai_chatbot.models.RegistrationRequest;
import com.dynatek.ai_chatbot.services.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody
            RegistrationRequest registrationRequest) {
        try {
            return ResponseEntity.ok(authService.register(registrationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .body(
                                         AuthResponse.builder()
                                                     .accessToken(null)
                                                     .refreshToken(null)
                                                     .build()
                                 );
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody
            AuthRequest registrationRequest) {
        try {
            return ResponseEntity.ok(authService.authenticate(registrationRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .body(
                                         AuthResponse.builder()
                                                     .accessToken(null)
                                                     .refreshToken(null)
                                                     .build()
                                 );
        }
    }

    @PostMapping(path = "/refresh")
    public void refresh(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}
