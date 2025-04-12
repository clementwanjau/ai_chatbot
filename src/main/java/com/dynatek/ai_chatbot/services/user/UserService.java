package com.dynatek.ai_chatbot.services.user;

import com.dynatek.ai_chatbot.models.ChangePasswordRequest;

import java.security.Principal;

/**
 * This interface defines the contract for user-related services.
 * It includes methods for changing a user's password.
 */
public interface UserService {
    /**
     * Change the password of the connected user.
     *
     * <p>
     * This method is responsible for changing the password of the currently
     * authenticated user. It takes a ChangePasswordRequest object that contains
     * the old password, new password, and confirmation of the new password.
     * </p>
     *
     * @param currentUser           The currently authenticated user.
     * @param changePasswordRequest The request containing the old and new passwords.
     */
    void changePassword(Principal currentUser, ChangePasswordRequest changePasswordRequest);
}
