package com.dynatek.ai_chatbot.services.auth;

import com.dynatek.ai_chatbot.models.AuthRequest;
import com.dynatek.ai_chatbot.models.AuthResponse;
import com.dynatek.ai_chatbot.models.RegistrationRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * This interface defines the contract for authentication-related services.
 * It includes methods for user registration, authentication, and token refresh.
 */
public interface AuthService {
    /**
     * Register a new user.
     *
     * <p>
     * This method is responsible for registering a new user in the system.
     * It takes a RegistrationRequest object that contains the user's details
     * such as username, password, and email.
     * </p>
     *
     * @param registrationRequest The request containing the user's registration details.
     * @return An AuthResponse object containing the access and refresh tokens.
     */
    AuthResponse register(RegistrationRequest registrationRequest);

    /**
     * Authenticate a user.
     *
     * <p>
     * This method is responsible for authenticating a user in the system.
     * It takes an AuthRequest object that contains the user's credentials
     * such as username and password.
     * </p>
     *
     * @param authRequest The request containing the user's authentication details.
     * @return An AuthResponse object containing the access and refresh tokens.
     */
    AuthResponse authenticate(AuthRequest authRequest);

    /**
     * Refresh the access token.
     *
     * <p>
     * This method is responsible for refreshing the access token of a user.
     * It takes an HttpServletRequest and HttpServletResponse object to
     * handle the request and response respectively.
     * </p>
     *
     * @param httpServletRequest  The HTTP request object.
     * @param httpServletResponse The HTTP response object.
     * @throws IOException If an I/O error occurs during the refresh process.
     */
    void refreshToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException;
}
