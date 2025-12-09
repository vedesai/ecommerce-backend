// AI Generated Code by Deloitte + Cursor (BEGIN)
package com.crestandthread.ecommerce.controller;

import com.crestandthread.ecommerce.dto.NewsletterSubscriptionDTO;
import com.crestandthread.ecommerce.exception.DuplicateResourceException;
import com.crestandthread.ecommerce.service.NewsletterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NewsletterController.class)
@AutoConfigureMockMvc(addFilters = false)
class NewsletterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NewsletterService newsletterService;

    private NewsletterSubscriptionDTO subscriptionDTO;

    @BeforeEach
    void setUp() {
        subscriptionDTO = NewsletterSubscriptionDTO.builder()
                .id(1L)
                .email("test@example.com")
                .active(true)
                .subscribedAt(LocalDateTime.now())
                .build();
    }

    @Nested
    @DisplayName("POST /api/v1/newsletter/subscribe")
    class Subscribe {

        @Test
        @DisplayName("should subscribe email successfully")
        void shouldSubscribeEmailSuccessfully() throws Exception {
            when(newsletterService.subscribe("test@example.com")).thenReturn(subscriptionDTO);

            mockMvc.perform(post("/api/v1/newsletter/subscribe")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"email\": \"test@example.com\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.data.email").value("test@example.com"));
        }

        @Test
        @DisplayName("should return 409 when email already subscribed")
        void shouldReturn409WhenEmailAlreadySubscribed() throws Exception {
            doThrow(new DuplicateResourceException("Newsletter subscription", "email", "test@example.com"))
                    .when(newsletterService).subscribe(anyString());

            mockMvc.perform(post("/api/v1/newsletter/subscribe")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"email\": \"test@example.com\"}"))
                    .andExpect(status().isConflict())
                    .andExpect(jsonPath("$.success").value(false));
        }

        @Test
        @DisplayName("should return 400 when email is invalid")
        void shouldReturn400WhenEmailIsInvalid() throws Exception {
            mockMvc.perform(post("/api/v1/newsletter/subscribe")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"email\": \"invalid-email\"}"))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("POST /api/v1/newsletter/unsubscribe")
    class Unsubscribe {

        @Test
        @DisplayName("should unsubscribe email successfully")
        void shouldUnsubscribeEmailSuccessfully() throws Exception {
            mockMvc.perform(post("/api/v1/newsletter/unsubscribe")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"email\": \"test@example.com\"}"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true));
        }
    }

    @Nested
    @DisplayName("GET /api/v1/newsletter/check")
    class CheckSubscription {

        @Test
        @DisplayName("should return true when email is subscribed")
        void shouldReturnTrueWhenEmailIsSubscribed() throws Exception {
            when(newsletterService.isSubscribed("test@example.com")).thenReturn(true);

            mockMvc.perform(get("/api/v1/newsletter/check")
                            .param("email", "test@example.com"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.success").value(true))
                    .andExpect(jsonPath("$.data").value(true));
        }
    }
}
// AI Generated Code by Deloitte + Cursor (END)