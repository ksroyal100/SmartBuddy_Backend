package com.smartbuddy.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.smartbuddy.backend.dto.SaveReviewRequest;
import com.smartbuddy.backend.entity.ReviewSession;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.service.ReviewSessionService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewSessionController {

    private final ReviewSessionService reviewSessionService;

    // ✅ MANUAL CONSTRUCTOR (NO LOMBOK)
    public ReviewSessionController(ReviewSessionService reviewSessionService) {
        this.reviewSessionService = reviewSessionService;
    }

    @PostMapping
    public ResponseEntity<ReviewSession> saveReview(
            @RequestBody SaveReviewRequest request,
            @AuthenticationPrincipal User user
    ) {
        ReviewSession saved = reviewSessionService.save(
                user,
                request.getCode(),
                request.getReviewHtml()
        );
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<ReviewSession> getAll(@AuthenticationPrincipal User user) {
        return reviewSessionService.getAll(user);
    }

    @GetMapping("/{id}")
    public ReviewSession getById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        return reviewSessionService.getById(id, user);
    }
}
