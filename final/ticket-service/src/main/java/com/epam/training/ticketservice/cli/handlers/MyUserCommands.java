package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.user.MyUserService;
import com.epam.training.ticketservice.core.user.entity.MyUser;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class MyUserCommands {

    private final MyUserService myUserService;

    @ShellMethod(key = "sign in", value = "Login")
    public String login(String username, String password) {
        Optional<MyUser> user = myUserService.login(username, password);
        return user.map(value -> "Successful login!")
                .orElse("Login failed due to incorrect credentials");
    }

    @ShellMethod(key = "sign in privileged", value = "Login")
    public String loginAdmin(String username, String password) {
        Optional<MyUser> user = myUserService.login(username, password);
        return user.map(value -> " Successful privileged login!")
                .orElse("Login failed due to incorrect credentials");
    }
}
