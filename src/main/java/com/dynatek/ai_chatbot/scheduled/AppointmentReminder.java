package com.dynatek.ai_chatbot.scheduled;

import com.dynatek.ai_chatbot.models.Appointment;
import com.dynatek.ai_chatbot.services.appointment.AppointmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AppointmentReminder {
    private final AppointmentService appointmentService;

    @Scheduled(fixedDelay = 1000)
    public void sendAppointmentReminders() {
        try {
            for (Appointment appointment : appointmentService.getUpcomingAppointments()) {
                appointment.notifyParticipants();
            }
        } catch (Exception e) {
            log.error("Error sending reminder: {}", e.getMessage());
        }
    }
}
