package com.dynatek.ai_chatbot.models;

import jakarta.persistence.*;
import lombok.*;

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
}
