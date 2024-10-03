package org.example.application.service;

import org.example.application.ports.out.SaveFeedbackPort;
import org.example.domain.Feedback;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class FeedbackServiceTest {

    @Mock
    private SaveFeedbackPort saveFeedbackPort;

    private FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        feedbackService = new FeedbackService(saveFeedbackPort);
    }

    @Test
    void submitFeedback_shouldSaveFeedback() {
        // Given
        Feedback feedback = new Feedback("name", "test@example.com", "Test feedback");

        // When
        feedbackService.submitFeedback(feedback);

        // Then
        verify(saveFeedbackPort).save(feedback);
    }
}
