package com.tamish.orderingsystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tamish.orderingsystem.entity.AppUser;
import com.tamish.orderingsystem.enums.Role;
import com.tamish.orderingsystem.repository.AppUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AdminInitializer {

    @Bean
    public ApplicationRunner createAdmin(AppUserRepository userRepo,
                                         PasswordEncoder passwordEncoder,
                                         @Value("${app.admin.username:}") String username,
                                         @Value("${app.admin.password:}") String password) {
        return args -> {
            if (username.isBlank() || password.isBlank()) {
                log.info("ADMIN_USERNAME / ADMIN_PASSWORD not set, skipping admin creation");
                return;
            }
            if (password.length() < 8) {
                throw new IllegalStateException("ADMIN_PASSWORD must be at least 8 characters");
            }
            if (userRepo.existsByUsername(username)) {
                return;
            }
            AppUser admin = new AppUser();
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setRole(Role.ADMIN);
            userRepo.save(admin);
            log.info("Admin user '{}' created", username);
        };
    }
}