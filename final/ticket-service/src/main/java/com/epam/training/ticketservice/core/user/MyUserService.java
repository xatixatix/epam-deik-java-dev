package com.epam.training.ticketservice.core.user;

import com.epam.training.ticketservice.core.user.entity.MyUser;

import java.util.Optional;

public interface MyUserService {

    Optional<MyUser> login(String username, String password);
}
