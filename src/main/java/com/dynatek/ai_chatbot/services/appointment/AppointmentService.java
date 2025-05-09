package com.dynatek.ai_chatbot.services.appointment;

import com.dynatek.ai_chatbot.models.Appointment;

import java.util.List;

/**
 * This interface defines the contract for appointment-related services.
 * It provides methods to save, retrieve, delete, and update appointments.
 */
public interface AppointmentService {
    /**
     * Save a new appointment.
     *
     * <p>
     * This method is responsible for persisting a new appointment in the
     * system. It can be used to create a new appointment for a user or
     * group of users.
     * </p>
     *
     * @param appointment The appointment to save.
     * @return The newly created appointment.
     */
    Appointment saveAppointment(Appointment appointment);

    /**
     * Retrieve an appointment by its ID.
     *
     * @param id The ID of the appointment to retrieve.
     * @return The appointment with the specified ID.
     */
    Appointment getAppointmentById(Long id);

    /**
     * Delete an appointment by its ID.
     *
     * <p>
     * This method is responsible for removing an appointment from the system.
     * It can be used to cancel an appointment or remove it from the
     * schedule.
     * </p>
     *
     * @param id The ID of the appointment to delete.
     */
    void deleteAppointment(Long id);

    /**
     * Update an existing appointment.
     *
     * <p>
     * This method is responsible for modifying the details of an existing
     * appointment. It can be used to change the date, time, or any other
     * information related to the appointment.
     * </p>
     *
     * @param id          The ID of the appointment to update.
     * @param appointment The updated appointment details.
     */
    void updateAppointment(Long id, Appointment appointment);


    /**
     * Retrieve a list of upcoming appointments.
     *
     * <p>
     * This method is responsible for fetching a list of appointments that
     * are scheduled for the future. It can be used to display a user's
     * upcoming appointments or to manage the schedule.
     * </p>
     *
     * @return A list of upcoming appointments.
     */
    List<Appointment> getUpcomingAppointments();

    /**
     * Retrieve a list of appointments for the current user.
     *
     * <p>
     * This method is responsible for fetching a list of appointments that
     * are associated with the current user. It can be used to display a
     * user's appointments or to manage their schedule.
     * </p>
     *
     * @return A list of appointments for the current user.
     */
    List<Appointment> getMyAppointments();
}
