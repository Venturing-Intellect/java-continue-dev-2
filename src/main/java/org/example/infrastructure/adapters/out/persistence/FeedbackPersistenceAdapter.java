package org.example.infrastructure.adapters.out.persistence;

import org.example.application.ports.out.SaveFeedbackPort;
import org.example.domain.Feedback;
import org.springframework.stereotype.Component;

@Component
class FeedbackPersistenceAdapter implements SaveFeedbackPort {

    private final FeedbackJpaRepository repository;

    FeedbackPersistenceAdapter(FeedbackJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Feedback feedback) {
        FeedbackEntity entity = new FeedbackEntity();
        entity.setName(feedback.getName());
        entity.setEmail(feedback.getEmail());
        entity.setContent(feedback.getContent());
        repository.save(entity);
    }

}
