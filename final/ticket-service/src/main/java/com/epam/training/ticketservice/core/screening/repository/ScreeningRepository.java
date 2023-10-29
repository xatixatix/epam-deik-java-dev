package com.epam.training.ticketservice.core.screening.repository;

import com.epam.training.ticketservice.core.screening.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, String> {

    List<Screening> findAllByRoomName(String roomName);
}
