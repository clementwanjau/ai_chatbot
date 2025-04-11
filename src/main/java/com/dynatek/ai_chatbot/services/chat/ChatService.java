package com.dynatek.ai_chatbot.services.chat;

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
    String processMessage(String message);
}
