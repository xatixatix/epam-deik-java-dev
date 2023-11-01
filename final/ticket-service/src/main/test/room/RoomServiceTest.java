package room;

import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.room.impl.RoomServiceImpl;
import com.epam.training.ticketservice.core.room.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RoomServiceTest {

    @Mock
    private RoomRepository roomRepositoryMock;

    private RoomService roomService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        roomService = new RoomServiceImpl(roomRepositoryMock);
    }

    @Test
    void createRoomCallsSave() {
        Room newRoom = new Room("newRoom", 5, 5);
        roomService.createRoom(newRoom);

        verify(roomRepositoryMock).save(newRoom);
    }

    @Test
    void updateRoomTestCallsSave() {
        Room newRoom = new Room("newRoom", 5, 5);
        roomService.createRoom(newRoom);

        Mockito.when(roomRepositoryMock.findByName("newRoom")).thenReturn(newRoom);

        Room updateRoom = new Room("newRoom", 10, 6);
        roomService.updateRoom(updateRoom);

        verify(roomRepositoryMock, times(2)).save(updateRoom);
    }

    @Test
    void deleteRoomTestCallsDelete() {
        Room newRoom = new Room("newRoom", 5, 5);
        roomService.createRoom(newRoom);

        Mockito.when(roomRepositoryMock.findByName("newRoom")).thenReturn(newRoom);
        roomService.deleteRoom("newRoom");

        verify(roomRepositoryMock).delete(newRoom);
    }

    @Test
    void findByNameTestReturnsCorrectRoom() {
        Room newRoom = new Room("newRoom", 5, 5);
        roomService.createRoom(newRoom);

        Mockito.when(roomRepositoryMock.findByName("newRoom")).thenReturn(newRoom);

        Room createdRoom = roomService.findByName("newRoom");

        assertEquals("newRoom", createdRoom.getName());
        assertEquals(5, createdRoom.getRows());
        assertEquals(5, createdRoom.getColumns());
    }
}
