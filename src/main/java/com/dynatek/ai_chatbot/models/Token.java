package com.dynatek.ai_chatbot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Token class represents a token entity in the database.
 * It contains fields for the token value, its status (revoked or expired),
 * the type of token, and a reference to the associated user.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long Id;

    /**
     * The token value.
     * This field is unique and is used to identify the token.
     */
    @Column(unique = true)
    private String token;

    /**
     * Indicates whether the token has been revoked.
     * This field is used to determine if the token is still valid.
     */
    private boolean isRevoked;

    /**
     * Indicates whether the token has expired.
     * This field is used to determine if the token is still valid.
     */
    private boolean isExpired;

    /**
     * The type of the token.
     * This field is used to determine the type of token (e.g., BEARER).
     */
    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    /**
     * The user associated with the token.
     * This field is used to link the token to a specific user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Enum representing the type of token.
     * This enum is used to define the different types of tokens that can be created.
     */
    public enum TokenType {
        /**
         * Represents a bearer token.
         * This type of token is used for authentication and authorization.
         */
        BEARER,
    }
}
