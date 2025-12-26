package com.smartbuddy.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbuddy.backend.entity.ReviewSession;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.repository.ReviewSessionRepository;

@Service
public class ReviewSessionService {

    private final ReviewSessionRepository repository;

    public ReviewSessionService(ReviewSessionRepository repository) {
        this.repository = repository;
    }

    // ✅ SAVE REVIEW
    public ReviewSession save(User user, String code, String reviewHtml) {
        ReviewSession session = new ReviewSession();
        session.setUser(user);
        session.setCode(code);
        session.setReviewHtml(reviewHtml);
        session.setCreatedAt(LocalDateTime.now());
        session.setTitle(generateTitle(code));

        return repository.save(session);
    }

    // ✅ GET ALL REVIEWS FOR USER
    public List<ReviewSession> getAll(User user) {
        return repository.findByUserOrderByCreatedAtDesc(user);
    }

    // ✅ GET SINGLE REVIEW
    public ReviewSession getById(Long id, User user) {
        ReviewSession session = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (!session.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        return session;
    }

    private String generateTitle(String code) {
        return code.length() > 30
                ? code.substring(0, 30) + "..."
                : code;
    }
}
