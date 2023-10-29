package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ShellComponent
@AllArgsConstructor
public class MovieCommands {

    private final MovieService movieService;

    @ShellMethod(key = "create movie", value = "Create a new movie")
    private String createMovie(String title, String genre, int length) {
        movieService.createMovie(new Movie(title, genre, length));
        return "New movie created, with the title: " + title;
    }

    @ShellMethod(key = "update movie", value = "Update a movie")
    private String updateMovie(String title, String genre, int length) {
        movieService.updateMovie(new Movie(title, genre, length));
        return "New movie created, with the title: " + title;
    }

    @ShellMethod(key = "delete movie", value = "Delete a movie by title")
    private String deleteMovie(String title) {
        movieService.deleteMovieByTitle(title);
        return "Movie successfully deleted";
    }

    @ShellMethod(key = "list movies", value = "List movies")
    private String listMovies() {
        List<Movie> movies = movieService.listMovies();
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        } else {
            return movies.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
