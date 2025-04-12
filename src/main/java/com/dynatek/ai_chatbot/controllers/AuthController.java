package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.ApiResponse;
import com.dynatek.ai_chatbot.models.AuthRequest;
import com.dynatek.ai_chatbot.models.AuthResponse;
import com.dynatek.ai_chatbot.models.RegistrationRequest;
import com.dynatek.ai_chatbot.services.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = "/register")
    public ApiResponse<AuthResponse> register(
            @RequestBody
            RegistrationRequest registrationRequest) {
        try {
            return ApiResponse.<AuthResponse>builder()
                              .status((short) 200)
                              .message("User registered successfully")
                              .data(Optional.of(authService.register(registrationRequest)))
                              .build();
        } catch (Exception e) {
            return ApiResponse.<AuthResponse>builder()
                              .status((short) 400)
                              .message(String.format("User registration failed. \n%s", e.getMessage()))
                              .data(Optional.empty())
                              .build();
        }
    }

    @PostMapping(path = "/login")
    public ApiResponse<AuthResponse> login(
            @RequestBody
            AuthRequest registrationRequest) {
        try {
            return ApiResponse.<AuthResponse>builder()
                              .status((short) 200)
                              .message("User logged in successfully")
                              .data(Optional.of(authService.authenticate(registrationRequest)))
                              .build();
        } catch (Exception e) {
            return ApiResponse.<AuthResponse>builder()
                              .status((short) 400)
                              .message(String.format("User login failed. \n%s", e.getMessage()))
                              .data(Optional.empty())
                              .build();
        }
    }

    @PostMapping(path = "/refresh")
    public void refresh(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        authService.refreshToken(request, response);
    }
}
