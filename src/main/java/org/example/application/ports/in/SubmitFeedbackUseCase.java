package org.example.application.ports.in;

import org.example.domain.Feedback;

public interface SubmitFeedbackUseCase {
    void submitFeedback(Feedback feedback);
}
