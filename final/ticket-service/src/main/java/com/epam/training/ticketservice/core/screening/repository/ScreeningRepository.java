package com.epam.training.ticketservice.core.screening.repository;

import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.screening.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, String> {

    Screening findByRoomNameAndMovieAndStartTime(String roomName, Movie movie, LocalDateTime startTime);

    List<Screening> findAllByRoomName(String roomName);
}
