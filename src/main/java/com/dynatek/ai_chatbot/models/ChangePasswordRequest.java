package com.dynatek.ai_chatbot.models;

import lombok.*;

/**
 * This class is used to represent the request to change the password.
 * It contains the old password, new password and confirm password.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChangePasswordRequest {
    /**
     * The old password of the user.
     */
    private String currentPassword;
    /**
     * The new password of the user.
     */
    private String newPassword;
    /**
     * The confirmation of the new password of the user.
     */
    private String confirmationPassword;
}
