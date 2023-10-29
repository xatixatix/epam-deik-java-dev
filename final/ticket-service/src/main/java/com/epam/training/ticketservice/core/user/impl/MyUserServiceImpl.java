package com.epam.training.ticketservice.core.user.impl;

import com.epam.training.ticketservice.core.user.MyUserService;
import com.epam.training.ticketservice.core.user.entity.MyUser;
import com.epam.training.ticketservice.core.user.repository.MyUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserServiceImpl implements MyUserService {

    private MyUserRepository myUserRepository;

    @Override
    public Optional<MyUser> login(String username, String password) {
        return myUserRepository.findByNameAndPassword(username, password);
    }
}
