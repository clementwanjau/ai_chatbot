package com.dynatek.ai_chatbot.repositories;

import com.dynatek.ai_chatbot.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository
        extends JpaRepository<Token, Long> {
    @Query(value = """
            SELECT t FROM Token t INNER JOIN User u\s
            ON t.user.id = u.id\s
            WHERE u.id = :id AND (t.isExpired = FALSE or t.isRevoked = FALSE)\s
            """)
    List<Token> findAllValidTokensForUser(Long id);

    Optional<Token> findByToken(String token);
}
