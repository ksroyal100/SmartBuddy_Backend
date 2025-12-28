package com.smartbuddy.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbuddy.backend.entity.ReviewSession;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.repository.ReviewSessionRepository;

@Service
@Transactional
public class ReviewSessionService {

    private final ReviewSessionRepository repository;

    public ReviewSessionService(ReviewSessionRepository repository) {
        this.repository = repository;
    }

    // ✅ SAVE REVIEW
    public ReviewSession save(User user, String code, String reviewHtml) {

        if (user == null) {
            throw new RuntimeException("Authenticated user is required");
        }

        ReviewSession session = new ReviewSession();
        session.setUser(user); // 🔥 CRITICAL
        session.setCode(code);
        session.setReviewHtml(reviewHtml);
        session.setCreatedAt(LocalDateTime.now());
        session.setTitle(generateTitle(code));

        return repository.save(session);
    }

    // ✅ GET ALL REVIEWS FOR USER (SIDEBAR)
    @Transactional(readOnly = true)
    public List<ReviewSession> getAll(User user) {

        if (user == null) {
            throw new RuntimeException("Authenticated user is required");
        }

        return repository.findByUserOrderByCreatedAtDesc(user);
    }

    // ✅ GET SINGLE REVIEW (CLICK SIDEBAR)
    @Transactional(readOnly = true)
    public ReviewSession getById(Long id, User user) {

        if (user == null) {
            throw new RuntimeException("Authenticated user is required");
        }

        ReviewSession session = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review session not found"));

        // 🛡️ HARD DEFENSE AGAINST BROKEN DATA
        if (session.getUser() == null) {
            throw new RuntimeException("Review session has no owner (data corruption)");
        }

        if (!session.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized access");
        }

        return session;
    }

    private String generateTitle(String code) {
        if (code == null || code.isBlank()) {
            return "Untitled Review";
        }
        return code.length() > 30
                ? code.substring(0, 30) + "..."
                : code;
    }
    
    @Transactional
    public void deleteById(Long id, User user) {

        ReviewSession session = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (!session.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        repository.delete(session);
    }

}
