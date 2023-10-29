package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    @Override
    public void createMovie(Movie newMovie) {
        movieRepository.save(newMovie);
    }

    @Override
    public void updateMovie(Movie updatedMovie) {
        Movie movieToUpdateDB = movieRepository.findByTitle(updatedMovie.getTitle());
        movieToUpdateDB.setGenre(updatedMovie.getGenre());
        movieToUpdateDB.setLength(updatedMovie.getLength());
        movieRepository.save(movieToUpdateDB);
    }

    @Override
    public void deleteMovieByTitle(String movieToDeleteTitle) {
        Movie movieToDelete = movieRepository.findByTitle(movieToDeleteTitle);
        movieRepository.delete(movieToDelete);
    }

    @Override
    public List<Movie> listMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }
}
