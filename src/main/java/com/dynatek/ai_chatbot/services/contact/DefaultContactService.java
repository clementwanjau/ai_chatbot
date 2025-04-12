package com.dynatek.ai_chatbot.services.contact;

import com.dynatek.ai_chatbot.DbException;
import com.dynatek.ai_chatbot.models.Contact;
import com.dynatek.ai_chatbot.repositories.contact.ContactRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Default implementation of the ContactService interface.
 * This service handles the business logic for managing contacts.
 */
@Slf4j
@AllArgsConstructor
@Service
public class DefaultContactService
        implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public Long save(Contact contact) {
        try {
            Contact savedContact = contactRepository.save(contact);
            return savedContact.getId();
        } catch (Exception e) {
            log.error("Error saving contact: {}", e.getMessage());
            throw new DbException("Error saving contact: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        try {
            return contactRepository.findById(id);
        } catch (Exception e) {
            log.error("Error retrieving contact with id '{}': {}", id, e.getMessage());
            throw new DbException("Error retrieving contact with id " + id + ": " + e.getMessage(), e);
        }
    }
}
