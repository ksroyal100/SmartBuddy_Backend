package com.smartbuddy.backend.config;

import com.smartbuddy.backend.entity.Role;
import com.smartbuddy.backend.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_USER").isEmpty()) {
                Role role = new Role();
                role.setName("ROLE_USER");
                roleRepository.save(role);
            }
        };
    }
}
