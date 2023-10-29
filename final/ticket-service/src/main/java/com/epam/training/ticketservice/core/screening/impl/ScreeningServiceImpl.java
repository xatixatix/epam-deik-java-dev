package com.epam.training.ticketservice.core.screening.impl;

import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.entity.Screening;
import com.epam.training.ticketservice.core.screening.repository.ScreeningRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    @Override
    public void createScreening(Screening screening) {
        screeningRepository.save(screening);
    }

    @Override
    public boolean isOverlapping(Screening screeningToCheck) {

        //TODO: this is not working
        List<Screening> screeningList = screeningRepository.findAllByRoomName(screeningToCheck.getRoom().getName());
        if (screeningList.isEmpty()) {
            return false;
        }
        boolean isOverlapping = false;
        for (Screening screening : screeningList) {
            if (    //screening starts, screeningToCheck can't start within the playtime
                    (screening.getStartTime().isBefore(screeningToCheck.getStartTime()) &&
                            screeningToCheck.getStartTime().isAfter(screening.getStartTime().plusMinutes(screening.getMovie().getLength()))) ||
                            //screeningToCheck starts, screening can't start within the playtime
                            (screeningToCheck.getStartTime().isBefore(screening.getStartTime()) &&
                                    screening.getStartTime().isAfter(screeningToCheck.getStartTime().plusMinutes(screeningToCheck.getMovie().getLength())))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Screening> listScreenings() {
        return screeningRepository.findAll();
    }
}
