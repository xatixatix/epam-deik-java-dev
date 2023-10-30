package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.entity.Screening;

import java.util.List;

public interface ScreeningService {
    void createScreening(Screening screening);

    void deleteScreening(Screening screening);

    boolean isOverlapping(Screening screeningToCheck);

    boolean isBreakTime(Screening screeningToCheck);

    List<Screening> listScreenings();
}
