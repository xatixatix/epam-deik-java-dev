package com.epam.training.ticketservice.core.room.repository;

import com.epam.training.ticketservice.core.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {

    Room findByName(String name);
}
