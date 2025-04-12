package com.dynatek.ai_chatbot.services.contact;

import com.dynatek.ai_chatbot.models.Contact;

import java.util.Optional;

/**
 * This interface defines the contract for the Contact service.
 * It provides methods to save and retrieve contact information.
 */
public interface ContactService {
    /**
     * Save a new contact.
     *
     * <p>
     * This method is responsible for persisting a new contact in the
     * system. It can be used to create a new contact for a user or
     * group of users.
     * </p>
     *
     * @param contact The contact to save.
     * @return The ID of the newly created contact.
     */
    Long save(Contact contact);

    /**
     * Retrieve a contact by its ID.
     *
     * @param id The ID of the contact to retrieve.
     * @return The contact with the specified ID.
     */
    Optional<Contact> getContactById(Long id);
}
