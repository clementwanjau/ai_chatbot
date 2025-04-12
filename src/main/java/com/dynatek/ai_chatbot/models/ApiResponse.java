package com.dynatek.ai_chatbot.models;

import lombok.*;

import java.util.Optional;

/**
 * This class is used to represent the response of an API call.
 * It contains the status, message and data of the response.
 *
 * @param <T> The type of the data in the response.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T extends Object> {
    /**
     * The http status code of the response.
     */
    private short status;
    /**
     * The message of the response.
     */
    private String message;
    /**
     * The data of the response.
     * This field is optional and may be empty if there is no data to return.
     */
    private Optional<T> data = Optional.empty();
}
