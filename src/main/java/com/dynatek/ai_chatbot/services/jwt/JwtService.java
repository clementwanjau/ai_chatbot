package com.dynatek.ai_chatbot.services.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;


/**
 * Interface for JWT (JSON Web Token) service.
 * Provides methods for token generation, validation, and claim extraction.
 */
public interface JwtService {
    /**
     * Extracts the username from the given JWT token.
     *
     * @param token The JWT token.
     * @return The username extracted from the token.
     */
    String extractUsername(String token);

    /**
     * Extracts a specific claim from the given JWT token.
     *
     * @param token          The JWT token.
     * @param claimsResolver A function to extract the claim.
     * @param <T>            The type of the claim.
     * @return The extracted claim.
     */
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    /**
     * Generates a JWT token for the given user details.
     *
     * @param userDetails The user details.
     * @return The generated JWT token.
     */
    String generateToken(UserDetails userDetails);

    /**
     * Generates a JWT token with additional claims for the given user details.
     *
     * @param extraClaims Additional claims to include in the token.
     * @param userDetails The user details.
     * @return The generated JWT token.
     */
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    /**
     * Generates a refresh token for the given user details.
     *
     * @param userDetails The user details.
     * @return The generated refresh token.
     */
    String generateRefreshToken(UserDetails userDetails);

    /**
     * Validates the given JWT token against the provided user details.
     *
     * @param token       The JWT token.
     * @param userDetails The user details to validate against.
     * @return True if the token is valid, false otherwise.
     */
    boolean isTokenValid(String token, UserDetails userDetails);

}
