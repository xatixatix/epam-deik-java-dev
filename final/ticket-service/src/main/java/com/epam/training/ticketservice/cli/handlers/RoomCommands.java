package com.epam.training.ticketservice.cli.handlers;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.entity.Room;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ShellComponent
@AllArgsConstructor
public class RoomCommands {

    RoomService roomService;

    @ShellMethod(key = "create room", value = "Create a new room")
    private String createRoom(String name, int rows, int columns) {
        roomService.createRoom(new Room(name, rows, columns));
        return "New room created, with the name: " + name;
    }

    @ShellMethod(key = "update room", value = "Update a room")
    private String updateRoom(String name, int rows, int columns) {
        roomService.updateRoom(new Room(name, rows, columns));
        return "Room updated successfully";
    }

    @ShellMethod(key = "delete room", value = "Delete a room")
    private String deleteRoom(String name) {
        roomService.deleteRoom(name);
        return "Room deleted successfully";
    }

    @ShellMethod(key = "list rooms", value = "List rooms")
    private String listRooms() {
        List<Room> rooms = roomService.listRooms();
        if(rooms.isEmpty()){
            return "There are no rooms at the moment";
        } else {
            return rooms.stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
