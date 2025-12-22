package com.smartbuddy.backend.service.impl;

import com.smartbuddy.backend.entity.AnalysisHistory;
import com.smartbuddy.backend.entity.User;
import com.smartbuddy.backend.repository.AnalysisHistoryRepository;
import com.smartbuddy.backend.repository.UserRepository;
import com.smartbuddy.backend.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisHistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveAnalysis(String username, String language, String code) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AnalysisHistory history = new AnalysisHistory();
        history.setUser(user);
        history.setLanguage(language);
        history.setCode(code);
        history.setCreatedAt(LocalDateTime.now());

        historyRepository.save(history);
    }
}
