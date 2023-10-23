package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class MovieCommands {

    private final MovieService movieService;

    @ShellMethod(key = "create movie", value = "Create a new movie")
    private String createMovie(String title, String genre, int length) {
        movieService.createMovie(new Movie(title, genre, length));
        return "New movie created, with the title: " + title;
    }
}
