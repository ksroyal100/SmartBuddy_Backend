package com.smartbuddy.backend.repository;

import com.smartbuddy.backend.entity.UserMetrics;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserMetricsRepository extends JpaRepository<UserMetrics, Long> {

    Optional<UserMetrics> findByUsername(String username);
}
