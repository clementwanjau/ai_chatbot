package com.dynatek.ai_chatbot.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Http401ForbiddenEntryPoint is a custom implementation of the AuthenticationEntryPoint interface.
 * It is used to handle authentication errors by sending a 401 Unauthorized response.
 * This class is annotated with @Component, making it a Spring-managed bean.
 */
@Component
public class Http401ForbiddenEntryPoint
        implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
