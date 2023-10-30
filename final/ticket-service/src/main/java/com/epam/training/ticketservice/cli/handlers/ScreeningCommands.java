package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.entity.Screening;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ShellComponent
@AllArgsConstructor
public class ScreeningCommands {

    private final MovieService movieService;
    private final RoomService roomService;
    private final ScreeningService screeningService;

    @ShellMethod(key = "create screening", value = "Create a new screening")
    private String createScreening(String movieName, String roomName, String startTime) throws ParseException {
        if (screeningService.isOverlapping(screeningCreator(movieName, roomName, startTime))) {
            return "There is an overlapping screening";
        } else if (screeningService.isBreakTime(screeningCreator(movieName, roomName, startTime))) {
            return "This would start in the break period after another screening in this room";
        } else {
            screeningService.createScreening(screeningCreator(movieName, roomName, startTime));
            return "New screening created successfully";
        }
    }

    @ShellMethod(key = "delete screening", value = "Create a new screening")
    private String deleteScreening(String movieName, String roomName, String startTime) throws ParseException {
        screeningService.deleteScreening(screeningCreator(movieName, roomName, startTime));
        return "Screening deleted successfully";
    }

    @ShellMethod(key = "list screenings", value = "List screenings")
    private String listScreenings() {
        List<Screening> screenings = screeningService.listScreenings();
        if (screenings.isEmpty()) {
            return "There are no screenings";
        } else {
            return screenings.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private Screening screeningCreator(String movieName, String roomName, String startTime) throws ParseException {
        return new Screening(
                movieService.findByTitle(movieName),
                roomService.findByName(roomName),
                LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
    }
}
