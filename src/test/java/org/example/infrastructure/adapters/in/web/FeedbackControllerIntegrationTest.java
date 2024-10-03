package org.example.infrastructure.adapters.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.application.ports.in.SubmitFeedbackUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(FeedbackController.class)
class FeedbackControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubmitFeedbackUseCase submitFeedbackUseCase;

    @Test
    void submitFeedback_withValidRequest_shouldReturnOk() throws Exception {
        // Given
        FeedbackController.FeedbackRequest request = new FeedbackController.FeedbackRequest("test@example.com", "Test feedback");

        // When & Then
        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Feedback submitted successfully"));
    }

    @Test
    void submitFeedback_withInvalidEmail_shouldReturnBadRequest() throws Exception {
        // Given
        FeedbackController.FeedbackRequest request = new FeedbackController.FeedbackRequest("invalid-email", "Test feedback");

        // When & Then
        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input: Invalid email format"));
    }

    @Test
    void submitFeedback_withBlankContent_shouldReturnBadRequest() throws Exception {
        // Given
        FeedbackController.FeedbackRequest request = new FeedbackController.FeedbackRequest("test@example.com", "");

        // When & Then
        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input: Content cannot be blank"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "test@example.com",
            "user.name+tag@example.com",
            "user-name@example.co.uk",
            "1234567890@example.com",
            "email@example-one.com",
            "_______@example.com"
    })
    void submitFeedback_withValidEmail_shouldReturnOk(String email) throws Exception {
        FeedbackController.FeedbackRequest request = new FeedbackController.FeedbackRequest(email, "Test feedback");

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Feedback submitted successfully"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "email@example@example.com",
            ".email@example.com",
            "email.@example.com",
            "email..email@example.com",
            "email@example.com (Joe Smith)",
            "email@example",
            "email@-example.com",
            "email@example..com",
            "Abc..123@example.com"
    })
    void submitFeedback_withInvalidEmail_shouldReturnBadRequest(String email) throws Exception {
        FeedbackController.FeedbackRequest request = new FeedbackController.FeedbackRequest(email, "Test feedback");

        mockMvc.perform(post("/api/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input: Invalid email format"));
    }


}
