package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.entity.Room;

import java.util.List;

public interface RoomService {

    void createRoom(Room newRoom);

    void updateRoom(Room updatedRoom);

    void deleteRoom(String roomToDelete);

    List<Room> listRooms();
}
