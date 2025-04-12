package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.ChangePasswordRequest;
import com.dynatek.ai_chatbot.services.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody
            ChangePasswordRequest changePasswordRequest,
            Principal currentUser
    ) {
        try {
            userService.changePassword(currentUser, changePasswordRequest);
            return ResponseEntity.ok()
                                 .build();
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .body("Error changing password: " + e.getMessage());
        }
    }
}
