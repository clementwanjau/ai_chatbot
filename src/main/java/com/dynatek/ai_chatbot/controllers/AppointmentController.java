package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.Appointment;
import com.dynatek.ai_chatbot.services.appointment.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    /**
     * Endpoint to retrieve all appointments for the authenticated user.
     *
     * @return List of appointments for the authenticated user.
     */
    @GetMapping
    public List<Appointment> getAllMyAppointments() {
        return appointmentService.getMyAppointments();
    }
}
