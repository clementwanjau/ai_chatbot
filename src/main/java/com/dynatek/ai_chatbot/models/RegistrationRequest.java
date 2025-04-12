package com.dynatek.ai_chatbot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to represent the request to register a new user.
 * It contains the first name, last name, email and password of the user.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    /**
     * The full name of the user.
     */
    private String fullName;
    /**
     * The email of the user.
     */
    private String email;
    /**
     * The password of the user.
     */
    private String password;
}
