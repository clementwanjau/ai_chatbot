package com.dynatek.ai_chatbot.services.appointment;

import com.dynatek.ai_chatbot.DbException;
import com.dynatek.ai_chatbot.ItemNotFoundException;
import com.dynatek.ai_chatbot.models.Appointment;
import com.dynatek.ai_chatbot.repositories.appointment.AppointmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
@Service
@Slf4j
public class DefaultAppointmentService
        implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        try {
            return appointmentRepository.save(appointment);
        } catch (Exception e) {
            log.error("Error saving appointment: {}", appointment.toString());
            throw new DbException(String.format("Error saving appointment: %s", e.getMessage()), e);
        }
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        try {
            return appointmentRepository.findById(id)
                                        .orElseThrow(() -> new ItemNotFoundException(String.format("Appointment with id %d not found.", id)));
        } catch (Exception e) {
            log.error("Error retrieving appointment with id '{}': {}", id, e.getMessage());
            throw new DbException(String.format("Error retrieving appointment with id %d: %s", id, e.getMessage()), e);
        }
    }

    @Override
    public void deleteAppointment(Long id) {
        try {
            if (!appointmentRepository.existsById(id)) {
                throw new ItemNotFoundException(String.format("Appointment with id %d not found.", id));
            }
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            log.error("Error deleting appointment with id '{}': {}", id, e.getMessage());
            throw new DbException(String.format("Error deleting appointment with id %d: %s", id, e.getMessage()), e);
        }
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        try {
            if (!appointmentRepository.existsById(id)) {
                throw new ItemNotFoundException(String.format("Appointment with id %d not found.", id));
            }
            appointmentRepository.save(appointment);
        } catch (Exception e) {
            log.error("Error updating appointment with id '{}': {}", id, e.getMessage());
            throw new DbException(String.format("Error updating appointment with id %d: %s", id, e.getMessage()), e);
        }
    }

    @Override
    public List<Appointment> getUpcomingAppointments() {
        // Get all the appointments whose scheduledAt is exactly 1 hour from now ignoring any precision below a minute
        // This is ugly because we run the scheduled task every minute and that means we'll run a call to the db
        // every single minute. Not the best idea.
        try {
            LocalDateTime oneHourFromNow =
                    LocalDateTime.now()
                                 .plusHours(1)
                                 .truncatedTo(ChronoUnit.MINUTES);
            return appointmentRepository.findAllByScheduledAtTruncatedToMinute(oneHourFromNow);
        } catch (Exception e) {
            log.error("Error getting upcoming appointments: {}", e.getMessage());
            throw new DbException(String.format("Error getting upcoming appointments: %s", e.getMessage()), e);
        }
    }
}
