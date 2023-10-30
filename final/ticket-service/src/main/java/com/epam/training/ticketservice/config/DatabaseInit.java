package com.epam.training.ticketservice.config;

import com.epam.training.ticketservice.core.user.entity.MyUser;
import com.epam.training.ticketservice.core.user.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("!prod")
@AllArgsConstructor
public class DatabaseInit {

    private final MyUserRepository myUserRepository;

    @PostConstruct
    public void init() {
        MyUser admin = new MyUser("admin", "admin", MyUser.Permission.ADMIN);
        myUserRepository.save(admin);
    }
}
