package com.epam.training.ticketservice.core.movie;

import com.epam.training.ticketservice.core.movie.entity.Movie;

import java.util.List;

public interface MovieService {

    void createMovie(Movie newMovie);

    void updateMovie(Movie updatedMovie);

    void deleteMovieByTitle(String movieToDeleteTitle);

    List<Movie> listMovies();

    Movie findByTitle(String title);
}
