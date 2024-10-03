package org.example.domain;

public class Feedback {
    private final String email;
    private final String content;

    public Feedback(String email, String content) {
        this.email = email;
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }
}
