package com.dynatek.ai_chatbot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * Represents a user in the chat application.
 * This class is used to store and manage user information. It is
 * also used to authenticate users.
 */
@Entity
@Table(name = "_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User
        implements UserDetails {
    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The email of the user.
     */
    private String username;
    /**
     * The password of the user.
     */
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.USER.toString()));
    }

    /**
     * A role is a set of permissions that a user has in the system.
     */
    public enum Role {
        /**
         * The user role.
         * This role is assigned to regular users of the system.
         */
        USER
    }
}
