package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.ApiResponse;
import com.dynatek.ai_chatbot.models.ChangePasswordRequest;
import com.dynatek.ai_chatbot.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping
    public ApiResponse<?> changePassword(
            @RequestBody
            ChangePasswordRequest changePasswordRequest,
            Principal currentUser
    ) {
        try {
            userService.changePassword(currentUser, changePasswordRequest);
            return ApiResponse.builder()
                              .status((short) 200)
                              .message("Password changed successfully")
                              .data(Optional.empty())
                              .build();
        } catch (Exception e) {
            return ApiResponse.builder()
                              .status((short) 400)
                              .message(String.format("Failed to change password for user. \n%s", e.getMessage()))
                              .data(Optional.empty())
                              .build();
        }
    }
}
