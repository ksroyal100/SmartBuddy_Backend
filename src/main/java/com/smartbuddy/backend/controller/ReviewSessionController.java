package com.smartbuddy.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.smartbuddy.backend.dto.SaveReviewRequest;
import com.smartbuddy.backend.entity.ReviewSession;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.repository.UserRepository;
import com.smartbuddy.backend.service.ReviewSessionService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewSessionController {

    private final ReviewSessionService reviewSessionService;
    private final UserRepository userRepository;

    public ReviewSessionController(
            ReviewSessionService reviewSessionService,
            UserRepository userRepository
    ) {
        this.reviewSessionService = reviewSessionService;
        this.userRepository = userRepository;
    }

    /* =========================
       GET ALL REVIEWS (HISTORY)
       ========================= */
    @GetMapping
    public ResponseEntity<List<ReviewSession>> getAll(Authentication authentication) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(reviewSessionService.getAll(user));
    }

    /* =========================
       GET SINGLE REVIEW
       ========================= */
    @GetMapping("/{id}")
    public ResponseEntity<ReviewSession> getById(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(reviewSessionService.getById(id, user));
    }

    /* =========================
       DELETE REVIEW
       ========================= */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            Authentication authentication
    ) {
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        reviewSessionService.deleteById(id, user);
        return ResponseEntity.noContent().build();
    }

    /* =========================
       SAVE REVIEW SESSION
       ========================= */
    @PostMapping
    public ResponseEntity<ReviewSession> save(
            @RequestBody SaveReviewRequest request,
            Authentication authentication
    ) {
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ReviewSession session = reviewSessionService.save(
                user,
                request.getCode(),
                request.getReviewHtml()
        );

        return ResponseEntity.ok(session);
    }
}
