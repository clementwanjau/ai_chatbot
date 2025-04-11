package com.dynatek.ai_chatbot.services.appointment;

import com.dynatek.ai_chatbot.ItemNotFoundException;
import com.dynatek.ai_chatbot.models.Appointment;
import com.dynatek.ai_chatbot.repositories.appointment.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@AllArgsConstructor
@Service
public class DefaultAppointmentService
        implements AppointmentService {
    private final Logger log = Logger.getLogger(DefaultAppointmentService.class.getName());

    private final AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) throws ItemNotFoundException {
        return appointmentRepository.findById(id)
                                    .orElseThrow(() -> new ItemNotFoundException(String.format("Appointment with id %d not found.", id)));
    }

    @Override
    public void deleteAppointment(Long id) throws ItemNotFoundException {
        if (!appointmentRepository.existsById(id)) {
            throw new ItemNotFoundException(String.format("Appointment with id %d not found.", id));
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public void updateAppointment(Long id, Appointment appointment) {
        if (!appointmentRepository.existsById(id)) {
            throw new ItemNotFoundException(String.format("Appointment with id %d not found.", id));
        }
        appointmentRepository.save(appointment);
    }

    @Override
    public void notifyParticipants(Appointment appointment) {
        log.info(String.format("Hello %s, you have an appointment scheduled on %s at %s with %s for viewing the property at %s.",
                        appointment.getTenantContact()
                                   .getName(),
                        appointment.getScheduledAt()
                                   .toLocalDate(),
                        appointment.getScheduledAt()
                                   .toLocalTime(),
                        appointment.getLandlordContact()
                                   .getName(),
                        appointment.getPropertyAddress()
                )
        );
    }
}
