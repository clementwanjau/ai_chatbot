package com.dynatek.ai_chatbot.repositories;

import com.dynatek.ai_chatbot.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE date_trunc('minute', a.scheduledAt) = :targetTimeTruncated")
    List<Appointment> findAllByScheduledAtTruncatedToMinute(
            @Param("targetTimeTruncated")
            LocalDateTime targetTimeTruncated);
}
