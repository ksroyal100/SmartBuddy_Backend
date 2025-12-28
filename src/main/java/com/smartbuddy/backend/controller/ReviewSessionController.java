package com.smartbuddy.backend.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.smartbuddy.backend.dto.SaveReviewRequest;
import com.smartbuddy.backend.entity.ReviewSession;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.repository.UserRepository;

import org.springframework.http.HttpStatus;
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

    @GetMapping
    public ResponseEntity<List<ReviewSession>> getAll(Principal principal) {

        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(reviewSessionService.getAll(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewSession> getById(
            @PathVariable Long id,
            Principal principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(reviewSessionService.getById(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            Principal principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        reviewSessionService.deleteById(id, user);

        return ResponseEntity.noContent().build();
    }

    
    @PostMapping
    public ResponseEntity<ReviewSession> save(
            @RequestBody SaveReviewRequest request,
            Principal principal
    ) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(
                reviewSessionService.save(user, request.getCode(), request.getReviewHtml())
        );
    }
}
