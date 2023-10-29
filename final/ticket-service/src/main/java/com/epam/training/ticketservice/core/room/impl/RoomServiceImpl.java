package com.epam.training.ticketservice.core.room.impl;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.room.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository;

    @Override
    public void createRoom(Room newRoom) {
        roomRepository.save(newRoom);
    }

    @Override
    public void updateRoom(Room updatedRoom) {
        Room roomToUpdate = roomRepository.findByName(updatedRoom.getName());
        roomToUpdate.setRows(updatedRoom.getRows());
        roomToUpdate.setColumns(updatedRoom.getColumns());
        roomRepository.save(roomToUpdate);
    }

    @Override
    public void deleteRoom(String roomToDelete) {
        roomRepository.delete(roomRepository.findByName(roomToDelete));
    }

    @Override
    public List<Room> listRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room findByName(String name) {
        return roomRepository.findByName(name);
    }
}
