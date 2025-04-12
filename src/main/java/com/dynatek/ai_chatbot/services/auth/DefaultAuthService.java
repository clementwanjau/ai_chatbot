package com.dynatek.ai_chatbot.services.auth;

import com.dynatek.ai_chatbot.DbException;
import com.dynatek.ai_chatbot.models.*;
import com.dynatek.ai_chatbot.repositories.TokenRepository;
import com.dynatek.ai_chatbot.repositories.UserRepository;
import com.dynatek.ai_chatbot.services.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultAuthService
        implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegistrationRequest registrationRequest) {
        User user =
                User.builder()
                    .name(registrationRequest.getFullName())
                    .username(registrationRequest.getEmail())
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .build();
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, token);
        return AuthResponse.builder()
                           .accessToken(token)
                           .refreshToken(refreshToken)
                           .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        User user = userRepository.findByUsername(authRequest.getUsername())
                                  .orElseThrow();
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllTokens(user);
        saveUserToken(user, token);
        return AuthResponse.builder()
                           .accessToken(token)
                           .refreshToken(refreshToken)
                           .build();
    }

    @Override
    public void refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        String refreshToken = authHeader.substring(7);
        String username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            User user = this.userRepository.findByUsername(username)
                                           .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllTokens(user);
                saveUserToken(user, accessToken);
                AuthResponse authResponse =
                        AuthResponse.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(refreshToken)
                                    .build();
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter()
                                   .write(authResponse.toString());
            }
        }
    }

    /**
     * Saves the user token in the database.
     *
     * @param user     The user for whom the token is being saved.
     * @param jwtToken The JWT token to be saved.
     */
    private void saveUserToken(User user, String jwtToken) {
        try {
            Token token =
                    Token.builder()
                         .user(user)
                         .token(jwtToken)
                         .tokenType(Token.TokenType.BEARER)
                         .isRevoked(false)
                         .isExpired(false)
                         .build();
            tokenRepository.save(token);
        } catch (Exception e) {
            log.error("Error saving token: {}", e.getMessage());
            throw new DbException("Error saving token", e);
        }
    }

    /**
     * Revokes all tokens for the given user.
     *
     * @param user The user whose tokens should be revoked.
     */
    private void revokeAllTokens(User user) {
        try {
            List<Token> validUserTokens = tokenRepository.findAllValidTokensForUser(user.getId());
            if (validUserTokens.isEmpty()) {
                return;
            }
            validUserTokens.forEach(token -> {
                token.setExpired(true);
                token.setRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
        } catch (Exception e) {
            log.error("Error revoking tokens: {}", e.getMessage());
            throw new DbException("Error revoking tokens", e);
        }
    }
}
