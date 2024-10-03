package org.example.infrastructure.adapters.in.web;

import jakarta.validation.Valid;
import org.example.application.ports.in.SubmitFeedbackUseCase;
import org.example.domain.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final SubmitFeedbackUseCase submitFeedbackUseCase;

    public FeedbackController(SubmitFeedbackUseCase submitFeedbackUseCase) {
        this.submitFeedbackUseCase = submitFeedbackUseCase;
    }

    @PostMapping
    public ResponseEntity<String> submitFeedback(@Valid @RequestBody FeedbackRequest request) {
        Feedback feedback = new Feedback(request.email(), request.content());
        submitFeedbackUseCase.submitFeedback(feedback);
        return ResponseEntity.ok("Feedback submitted successfully");
    }

    record FeedbackRequest(
            @jakarta.validation.constraints.Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
            String email,
            @jakarta.validation.constraints.NotBlank(message = "Content cannot be blank")
            String content
    ) {}
}
