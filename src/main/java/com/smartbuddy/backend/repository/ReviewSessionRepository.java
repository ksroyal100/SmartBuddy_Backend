package com.smartbuddy.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbuddy.backend.entity.ReviewSession;
import com.smartbuddy.backend.entity.User;

public interface ReviewSessionRepository
        extends JpaRepository<ReviewSession, Long> {

    List<ReviewSession> findByUserOrderByCreatedAtDesc(User user);
}
