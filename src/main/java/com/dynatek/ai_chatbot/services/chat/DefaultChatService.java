package com.dynatek.ai_chatbot.services.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link ChatService} interface.
 * <p>
 * This class is responsible for processing messages sent by the user and
 * returning the response from the LLM.
 * </p>
 */
@Service
public class DefaultChatService
        implements ChatService {

    private final ChatClient chatClient;

    @Value("classpath:prompts/template.st")
    private Resource ragPromptTemplate;

    DefaultChatService(ChatClient.Builder clientBuilder) {
        this.chatClient = clientBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }

    @Override
    public String processMessage(String message) {
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("message", message);
        Prompt prompt = promptTemplate.create(promptParameters);
        return chatClient.prompt(prompt)
                         .call()
                         .content();
    }
}
