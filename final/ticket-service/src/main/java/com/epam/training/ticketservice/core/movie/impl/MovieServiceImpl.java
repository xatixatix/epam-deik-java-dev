package com.epam.training.ticketservice.core.movie.impl;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.movie.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    MovieRepository movieRepository;

    @Override
    public void createMovie(Movie newMovie) {
        movieRepository.save(newMovie);
    }
}
