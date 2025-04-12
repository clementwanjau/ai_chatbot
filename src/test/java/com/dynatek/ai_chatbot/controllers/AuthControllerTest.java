package com.dynatek.ai_chatbot.controllers;

import com.dynatek.ai_chatbot.models.AuthRequest;
import com.dynatek.ai_chatbot.models.AuthResponse;
import com.dynatek.ai_chatbot.models.User;
import com.dynatek.ai_chatbot.repositories.UserRepository;
import com.dynatek.ai_chatbot.services.auth.AuthService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void init() {
        setupUser();
    }

    private void setupUser() {
        User user = User.builder()
                        .id(1L)
                        .name("Jane Doe")
                        .username("janedoe@example.com")
                        .password("testpassword")
                        .build();
        userRepository.save(user);
    }

    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(
                       MockMvcRequestBuilders.post("/api/v1/auth/register")
                                             .header("Content-Type", "application/json")
                                             .accept(MediaType.APPLICATION_JSON)
                                             .content("{\"fullName\": \"Jane Doe\", \"email\":\"janedoe@example.com\",\"password\":\"testpassword\"}")
               )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value(200))
               .andExpect(jsonPath("$.message").value("User registered successfully"))
               .andExpect(jsonPath("$.data").exists());
    }

    @Test
    public void testLogin() throws Exception {
        AuthRequest testCredentials = AuthRequest.builder()
                                                 .username("janedoe@example.com")
                                                 .password("testpassword")
                                                 .build();
        when(authService.authenticate(testCredentials)).thenReturn(
                AuthResponse.builder()
                            .refreshToken("sampleToken")
                            .accessToken("refreshToken")
                            .build()
        );
        mockMvc.perform(
                       MockMvcRequestBuilders.post("/api/v1/auth/login")
                                             .header("Content-Type", "application/json")
                                             .accept(MediaType.APPLICATION_JSON)
                                             .content("{\"username\": \"janedoe@example.com\", \"password\": \"testpassword\"}")
               )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.status").value(200))
               .andExpect(jsonPath("$.message").value("User logged in successfully"))
               .andExpect(jsonPath("$.data").exists());
    }

}
