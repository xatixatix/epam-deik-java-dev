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
    public void deleteScreening(Screening screening) {
        screeningRepository.delete(screeningRepository.findByRoomNameAndMovieAndStartTime(
                screening.getRoom().getName(),
                screening.getMovie(),
                screening.getStartTime()
        ));
    }

    @Override
    public boolean isOverlapping(Screening screeningToCheck) {
        return overlapHelper(screeningToCheck, 0L);
    }

    @Override
    public boolean isBreakTime(Screening screeningToCheck) {
        return overlapHelper(screeningToCheck, 10L);
    }

    private boolean overlapHelper(Screening screeningToCheck, Long timeToAddAtEnd) {
        List<Screening> screeningList = screeningRepository.findAllByRoomName(screeningToCheck.getRoom().getName());

        if (screeningList.isEmpty()) {
            return false;
        }

        for (Screening screening : screeningList) {
            if (    //screening starts, screeningToCheck can't start within the playtime + timeToAddAtEnd
                    (screening.getStartTime().isBefore(screeningToCheck.getStartTime()) &&
                            screeningToCheck.getStartTime().isAfter(screening.getStartTime().plusMinutes(screening.getMovie().getLength() + timeToAddAtEnd))) ||
                            //screeningToCheck starts, screening can't start within the playtime + timeToAddAtEnd
                            (screeningToCheck.getStartTime().isBefore(screening.getStartTime()) &&
                                    screening.getStartTime().isAfter(screeningToCheck.getStartTime().plusMinutes(screeningToCheck.getMovie().getLength() + timeToAddAtEnd)))) {
                //nothing happens
            } else {
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
