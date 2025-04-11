package com.dynatek.ai_chatbot.repositories.appointment;

import com.dynatek.ai_chatbot.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
}
