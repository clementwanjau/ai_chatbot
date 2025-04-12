package com.dynatek.ai_chatbot.repositories;

import com.dynatek.ai_chatbot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository
        extends JpaRepository<User, Long> {
    /**
     * Find a user by their username.
     *
     * @param username The username of the user to find.
     * @return An Optional containing the user if found, or empty if not.
     */
    Optional<User> findByUsername(String username);
}
