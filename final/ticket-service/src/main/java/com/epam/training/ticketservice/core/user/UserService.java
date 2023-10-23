package com.epam.training.ticketservice.core.user;

import com.epam.training.ticketservice.core.user.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> login(String username, String password);
}
