package com.dynatek.ai_chatbot.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


/**
 * Represents an appointment in the chat application.
 * This class is used to store and manage appointment information. This information is
 * parsed from the user's message and stored in the database.
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;
    /**
     * The time that the appointment is scheduled for.
     */
    private LocalDateTime scheduledAt;
    /**
     * The address of the property that the appointment is for.
     */
    private String propertyAddress;
    /**
     * The contact information for the landlord of the property that the appointment is for.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Contact landlordContact;
    /**
     * The contact information for the tenant that is scheduling the appointment.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Contact tenantContact;

    /**
     * Notify participants about the appointment.
     *
     * <p>
     * This method is responsible for sending notifications to all participants
     * of the appointment. It can be used to inform them about the details
     * of the appointment, any changes made, or reminders.
     * </p>
     */
    public void notifyParticipants() {
        log.info("Hello {}, you have an appointment scheduled on {} at {} with {} for viewing the property at {}.",
                this.getTenantContact()
                    .getName(),
                this.getScheduledAt()
                    .toLocalDate(),
                this.getScheduledAt()
                    .toLocalTime(),
                this.getLandlordContact()
                    .getName(),
                this.getPropertyAddress()
        );
    }
}
