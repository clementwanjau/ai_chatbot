package com.dynatek.ai_chatbot;

import java.io.Serial;

/**
 * Custom exception class to handle item not found scenarios.
 * <p>
 * This exception is thrown when an item is not found in the database or any other data source.
 * </p>
 */
public class ItemNotFoundException
        extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ItemNotFoundException(String message) {
        super(message);
    }
}
