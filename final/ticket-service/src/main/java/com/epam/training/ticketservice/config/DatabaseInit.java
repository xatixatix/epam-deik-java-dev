package com.epam.training.ticketservice.config;

import com.epam.training.ticketservice.core.user.entity.User;
import com.epam.training.ticketservice.core.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class DatabaseInit {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User admin = new User("admin", "admin", User.PERMISSION.ADMIN);
        userRepository.save(admin);
    }
}
