package com.dynatek.ai_chatbot.services.chat;

import com.dynatek.ai_chatbot.models.LLMApiResponse;

public interface ChatService {
    /**
     * This method is used to process the message sent by the user.
     * <p>
     * It runs the message through the LLM and returns the response.
     * </p>
     *
     * @param message The raw message sent by the user.
     * @return The response from the LLM.
     */
    LLMApiResponse processMessage(String message);
}
