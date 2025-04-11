package com.dynatek.ai_chatbot.models;


import com.fasterxml.jackson.annotation.JsonPropertyDescription;


public record LLMApiResponse(
        @JsonPropertyDescription("success if if all the data was provided and failure if we have invalid or missing data.")
        ParsingResult status,
        @JsonPropertyDescription("The message confirming the appointment details or reason for failure")
        String message,
        @JsonPropertyDescription("The parsed appointment object containing the details of the appointment")
        Appointment appointment) {
    public enum ParsingResult {
        SUCCESS,
        FAILURE;

        public boolean isSuccess() {
            return this == SUCCESS;
        }
    }
}
