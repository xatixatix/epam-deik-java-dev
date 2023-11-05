package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.booking.BookingService;
import com.epam.training.ticketservice.core.booking.entity.Booking;
import com.epam.training.ticketservice.core.booking.entity.Seat;
import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.entity.Screening;
import com.epam.training.ticketservice.core.user.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@ShellComponent
@AllArgsConstructor
public class BookingCommands {

    private final BookingService bookingService;
    private final RoomService roomService;
    private final MovieService movieService;
    private final MyUserService myUserService;
    private final ScreeningService screeningService;

    @ShellMethod(key = "book", value = "Create a new booking")
    private String createBooking(String movieTitle, String roomName, String startTime, String seats) {
        Movie selectedMovie = movieService.findByTitle(movieTitle);
        Room selectedRoom = roomService.findByName(roomName);
        Screening selectedScreening = screeningService.findByMovieAndStartTime(
                selectedMovie,
                selectedRoom,
                LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        String seatsValues = String.valueOf(Integer.parseInt(seats.replaceAll("[\\D]", "")));
        List<Seat> seatsList = new ArrayList<>();
        for (int i = 0; i < seatsValues.length(); i += 2) {
            Seat newSeat = new Seat(
                    Character.getNumericValue(seatsValues.charAt(i)),
                    Character.getNumericValue(seatsValues.charAt(i + 1))
            );

            seatsList.add(newSeat);
        }

        bookingService.createBooking(new Booking(myUserService.userLoggedIn(), selectedScreening, seatsList));
        return "Booking created successfully";
    }
}
