package com.dynatek.ai_chatbot.services.chat;

import com.dynatek.ai_chatbot.models.LLMApiResponse;
import com.dynatek.ai_chatbot.services.appointment.AppointmentService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

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
    private final AppointmentService appointmentService;

    private final ChatClient chatClient;

    @Value("classpath:prompts/template.st")
    private Resource ragPromptTemplate;

    DefaultChatService(ChatClient.Builder clientBuilder, AppointmentService appointmentService) {
        this.chatClient = clientBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
        this.appointmentService = appointmentService;
    }

    @Override
    public LLMApiResponse processMessage(String message) {
        BeanOutputConverter<LLMApiResponse> converter = new BeanOutputConverter<>(LLMApiResponse.class);
        String responseFormat = converter.getFormat();
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of(
                "message", message,
                "format", responseFormat
        ));
        ChatResponse response = chatClient.prompt(prompt)
                                          .call()
                                          .chatResponse();
        assert response != null;
        LLMApiResponse apiResponse =
                converter.convert(
                        response.getResult()
                                .getOutput()
                                .getText()
                );
        assert apiResponse != null;
        if (apiResponse.status()
                       .isSuccess()) {
            // Persist the appointment to the database
            appointmentService.saveAppointment(apiResponse.appointment());
        }
        return apiResponse;
    }
}
