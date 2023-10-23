package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
@AllArgsConstructor
public class UserCommands {

    private final UserService userService;

    @ShellMethod(key = "sign in", value = "Login")
    public String login(String username, String password) {
        Optional<User> user = userService.login(username, password);
        return user.map(value -> "Successful login!")
                .orElse("Login failed due to incorrect credentials");
    }

    @ShellMethod(key = "sign in privileged", value = "Login")
    public String loginAdmin(String username, String password) {
        Optional<User> user = userService.login(username, password);
        return user.map(value -> " Successful privileged login!")
                .orElse("Login failed due to incorrect credentials");
    }
}
