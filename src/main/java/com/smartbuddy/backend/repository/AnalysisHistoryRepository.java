package com.smartbuddy.backend.repository;

import com.smartbuddy.backend.entity.AnalysisHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisHistoryRepository
        extends JpaRepository<AnalysisHistory, Long> {
}
