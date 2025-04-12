package com.dynatek.ai_chatbot.repositories.contact;

import com.dynatek.ai_chatbot.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository
        extends JpaRepository<Contact, Long> {
}
