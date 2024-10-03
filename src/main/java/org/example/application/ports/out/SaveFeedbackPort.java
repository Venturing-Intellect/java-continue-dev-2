package org.example.application.ports.out;

import org.example.domain.Feedback;

public interface SaveFeedbackPort {
    void save(Feedback feedback);
}
