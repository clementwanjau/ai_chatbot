package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.LLMApiResponse;
import com.dynatek.ai_chatbot.services.chat.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public LLMApiResponse handleMessage(
            @RequestParam(value = "message")
            String message
    ) {
        return chatService.processMessage(message);
    }
}
