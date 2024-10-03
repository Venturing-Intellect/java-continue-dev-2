package org.example.infrastructure.adapters.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface FeedbackJpaRepository extends JpaRepository<FeedbackEntity, Long> {
}
