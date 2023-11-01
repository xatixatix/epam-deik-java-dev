package com.epam.training.ticketservice.cli.handlers;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class BookingCommands {

    @ShellMethod(key = "book", value = "Create a new booking")
    private String createBooking(String movieTitle, String roomName, String startTime, String seats) {
        return "";
    }
}
