package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.ApiResponse;
import com.dynatek.ai_chatbot.models.LLMApiResponse;
import com.dynatek.ai_chatbot.services.chat.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping
    public ApiResponse<LLMApiResponse> handleMessage(
            @RequestParam(value = "message")
            String message
    ) {
        try {
            return ApiResponse.<LLMApiResponse>builder()
                              .status((short) 200)
                              .message("Message processed successfully")
                              .data(Optional.of(chatService.processMessage(message)))
                              .build();
        } catch (Exception e) {
            return ApiResponse.<LLMApiResponse>builder()
                              .status((short) 400)
                              .message(String.format("Failed to process message. \n%s", e.getMessage()))
                              .data(Optional.empty())
                              .build();
        }
    }
}
