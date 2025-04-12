package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.ApiResponse;
import com.dynatek.ai_chatbot.models.Appointment;
import com.dynatek.ai_chatbot.services.appointment.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    /**
     * Endpoint to retrieve all appointments for the authenticated user.
     *
     * @return List of appointments for the authenticated user.
     */
    @GetMapping
    public ApiResponse<List<Appointment>> getAllMyAppointments() {
        try {
            return ApiResponse.<List<Appointment>>builder()
                              .status((short) 200)
                              .message("Appointments retrieved successfully")
                              .data(Optional.of(appointmentService.getMyAppointments()))
                              .build();
        } catch (Exception e) {
            return ApiResponse.<List<Appointment>>builder()
                              .status((short) 400)
                              .message(String.format("Failed to retrieve appointments. \n%s", e.getMessage()))
                              .data(Optional.empty())
                              .build();
        }
    }
}
