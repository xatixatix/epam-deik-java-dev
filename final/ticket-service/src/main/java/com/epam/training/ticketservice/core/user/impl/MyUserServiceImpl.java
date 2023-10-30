package com.epam.training.ticketservice.core.user.impl;

import com.epam.training.ticketservice.core.user.MyUserService;
import com.epam.training.ticketservice.core.user.entity.MyUser;
import com.epam.training.ticketservice.core.user.repository.MyUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserServiceImpl implements MyUserService {

    private final MyUserRepository myUserRepository;
    private Optional<MyUser> userLoggedIn = Optional.empty();

    public MyUserServiceImpl(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public Optional<MyUser> login(String username, String password) {
        Optional<MyUser> user = myUserRepository.findByNameAndPassword(username, password);
        if (user.isPresent()) {
            userLoggedIn = user;
        }
        return userLoggedIn;
    }

    @Override
    public void signOut() {
        userLoggedIn = Optional.empty();
    }

    @Override
    public void register(MyUser newUser) {
        myUserRepository.save(newUser);
    }

    @Override
    public String describe() {
        if (userLoggedIn.isEmpty()) {
            return "You are not signed in";
        } else if (userLoggedIn.get().getPermission() == MyUser.PERMISSION.USER) {
            return "Signed in with account '" + userLoggedIn.get().getName() + "'";
        } else if (userLoggedIn.get().getPermission() == MyUser.PERMISSION.ADMIN) {
            return "Signed in with privileged account '" + userLoggedIn.get().getName() + "'";
        } else {
            return "Not implemented yet.";
        }

        //TODO: list tickets
    }
}
