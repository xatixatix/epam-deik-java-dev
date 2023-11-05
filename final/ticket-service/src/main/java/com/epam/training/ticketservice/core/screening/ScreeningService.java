package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.screening.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningService {
    void createScreening(Screening screening);

    void deleteScreening(Screening screening);

    boolean isOverlapping(Screening screeningToCheck);

    boolean isBreakTime(Screening screeningToCheck);

    List<Screening> listScreenings();

    Screening findByMovieAndStartTime(Movie movie, Room room, LocalDateTime startTime);
}
