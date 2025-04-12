package com.dynatek.ai_chatbot.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is used to represent the response of the authentication request.
 * It contains the access token and refresh token of the user.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    /**
     * The access token of the user.
     * This token is used to authenticate the user for subsequent requests.
     */
    @JsonProperty("access_token")
    private String accessToken;
    /**
     * The refresh token of the user.
     * This token is used to refresh the access token when it expires.
     */
    @JsonProperty("refresh_token")
    private String refreshToken;
}
