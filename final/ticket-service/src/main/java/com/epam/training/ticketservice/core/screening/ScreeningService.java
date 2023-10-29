package com.epam.training.ticketservice.core.screening;

import com.epam.training.ticketservice.core.screening.entity.Screening;

import java.util.List;

public interface ScreeningService {
    void createScreening(Screening screening);

    boolean isOverlapping(Screening screeningToCheck);

    List<Screening> listScreenings();
}
