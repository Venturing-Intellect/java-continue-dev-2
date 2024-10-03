package org.example.application.service;

import org.example.application.ports.in.SubmitFeedbackUseCase;
import org.example.application.ports.out.SaveFeedbackPort;
import org.example.domain.Feedback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackService implements SubmitFeedbackUseCase {

    private final SaveFeedbackPort saveFeedbackPort;

    public FeedbackService(SaveFeedbackPort saveFeedbackPort) {
        this.saveFeedbackPort = saveFeedbackPort;
    }

    @Override
    @Transactional
    public void submitFeedback(Feedback feedback) {
        saveFeedbackPort.save(feedback);
    }
}
