package screening;

import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.screening.ScreeningService;
import com.epam.training.ticketservice.core.screening.entity.Screening;
import com.epam.training.ticketservice.core.screening.impl.ScreeningServiceImpl;
import com.epam.training.ticketservice.core.screening.repository.ScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

class ScreeningServiceTest {

    @Mock
    private ScreeningRepository screeningRepositoryMock;

    private ScreeningService screeningService;

    private Room testRoom;
    private Movie testMovie;
    private LocalDateTime testStartTime;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        screeningService = new ScreeningServiceImpl(screeningRepositoryMock);

        testRoom = new Room("newRoom", 5, 5);
        testMovie = new Movie("newMovie", "Fantasy", 100);
        testStartTime = LocalDateTime.parse("2021-03-15 10:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Test
    void createScreeningCallsSave() {
        Screening newScreening = new Screening(testMovie, testRoom, testStartTime);

        screeningService.createScreening(newScreening);

        verify(screeningRepositoryMock).save(newScreening);
    }

    @Test
    void deleteScreeningCallsSave() {
        Screening newScreening = new Screening(testMovie, testRoom, testStartTime);

        Mockito.when(screeningService.findByMovieAndStartTime(
                testMovie,
                testRoom,
                LocalDateTime.parse("2021-03-15 10:45", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                ).thenReturn(newScreening);

        screeningService.deleteScreening(newScreening);

        verify(screeningRepositoryMock).delete(newScreening);
    }

    @Test
    void overlappingScreeningReturnsTrue() {
        List<Screening> screeningList = new ArrayList<>();
        screeningList.add(new Screening(testMovie, testRoom, testStartTime));
        Screening newOverlappingScreening = new Screening(
                testMovie,
                testRoom,
                LocalDateTime.parse("2021-03-15 11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        Mockito.when(screeningRepositoryMock.findAllByRoomName(newOverlappingScreening.getRoom().getName())).thenReturn(screeningList);

        assertTrue(screeningService.isOverlapping(newOverlappingScreening));
    }

    @Test
    void overlappingScreeningReturnsFalseAfterComparingScreenings() {
        List<Screening> screeningList = new ArrayList<>();
        screeningList.add(new Screening(testMovie, testRoom, testStartTime));
        Screening newOverlappingScreening = new Screening(
                testMovie,
                testRoom,
                LocalDateTime.parse("2021-03-15 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        Mockito.when(screeningRepositoryMock.findAllByRoomName(newOverlappingScreening.getRoom().getName())).thenReturn(screeningList);

        assertTrue(screeningService.isOverlapping(newOverlappingScreening));
    }

    @Test
    void overlappingScreeningReturnsFalseWhenNoScreeningIsReturned() {
        Screening newScreening = new Screening(testMovie, testRoom, testStartTime);

        assertFalse(screeningService.isOverlapping(newScreening));
    }
}
