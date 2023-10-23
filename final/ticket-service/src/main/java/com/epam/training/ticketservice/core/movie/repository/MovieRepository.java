package com.epam.training.ticketservice.core.movie.repository;

import com.epam.training.ticketservice.core.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, String> {

    Movie findByTitle(String title);
}
