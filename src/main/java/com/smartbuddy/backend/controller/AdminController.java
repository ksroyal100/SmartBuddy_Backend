package com.smartbuddy.backend.controller;

import java.awt.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbuddy.backend.repository.UserMetricsRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserMetricsRepository metricsRepo;

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/metrics")
//    public List<UserMetrics> getMetrics() {
//        return metricsRepo.findAll();
//    }
}

