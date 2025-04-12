package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.DbException;
import com.dynatek.ai_chatbot.models.Appointment;
import com.dynatek.ai_chatbot.models.Contact;
import com.dynatek.ai_chatbot.services.appointment.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
        // Simulate an authenticated user
    void getAllMyAppointments_returnsOkAndAppointments() throws Exception {
        // Arrange
        List<Appointment> mockAppointments = Collections.singletonList(
                Appointment.builder()
                           .id(1L)
                           .landlordContact(
                                   Contact.builder()
                                          .id(1L)
                                          .name("Jane Doe")
                                          .email("janedoe@example.com")
                                          .build()
                           )
                           .tenantContact(
                                   Contact.builder()
                                          .id(1L)
                                          .email("tenant@example.com")
                                          .name("Tenant Doe")
                                          .build()
                           )
                           .propertyAddress("Test Address")
                           .scheduledAt(
                                   LocalDateTime.now()
                                                .plusDays(7)
                           )
                           .build()
        );
        when(appointmentService.getMyAppointments()).thenReturn(mockAppointments);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointments")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                                               .value(200))
               .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                                               .value("Appointments retrieved successfully"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                                               .exists())
               .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].id")
                                               .value(1))
               .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].landlordContact.name")
                                               .value("Jane Doe"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].propertyAddress")
                                               .value("Test Address"));
    }

    @Test
    @WithMockUser
        // Simulate an authenticated user
    void getAllMyAppointments_returnsOkAndEmptyListIfNoAppointments() throws Exception {
        // Arrange
        when(appointmentService.getMyAppointments()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointments")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                                               .value(200))
               .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                                               .value("Appointments retrieved successfully"))
               .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                                               .exists());
    }

    @Test
    @WithMockUser
        // Simulate an authenticated user
    void getAllMyAppointments_returnsBadRequestOnError() throws Exception {
        // Arrange
        String errorMessage = "Database connection error";
        when(appointmentService.getMyAppointments()).thenThrow(new DbException(errorMessage));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointments")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.jsonPath("$.status")
                                               .value(400))
               .andExpect(MockMvcResultMatchers.jsonPath("$.message")
                                               .value(String.format("Failed to retrieve appointments. \n%s", errorMessage)))
               .andExpect(MockMvcResultMatchers.jsonPath("$.data")
                                               .doesNotExist());
    }

    @Test
    void getAllMyAppointments_unauthenticatedUserReturnsUnauthorized() throws Exception {
        // Arrange (no @WithMockUser, so no authenticated user)

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/appointments")
                                              .contentType(MediaType.APPLICATION_JSON))
               .andExpect(MockMvcResultMatchers.status()
                                               .isUnauthorized());
    }
}
