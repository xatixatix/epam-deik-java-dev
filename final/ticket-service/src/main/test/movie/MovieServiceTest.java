package movie;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.movie.impl.MovieServiceImpl;
import com.epam.training.ticketservice.core.movie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepositoryMock;
    private MovieService movieService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieServiceImpl(movieRepositoryMock);
    }

    @Test
    void createMovieTestCallsSave() {

        Movie newMovie = new Movie("newMovie", "Fantasy", 100);

        movieService.createMovie(newMovie);

        verify(movieRepositoryMock).save(newMovie);
    }

    @Test
    void updateMovieTestCallsSave() {
        Movie newMovie = new Movie("newMovie", "Fantasy", 100);
        movieService.createMovie(newMovie);

        Mockito.when(movieRepositoryMock.findByTitle("newMovie")).thenReturn(newMovie);

        Movie updatedMovie = new Movie("newMovie", "Action", 250);
        movieService.updateMovie(updatedMovie);

        verify(movieRepositoryMock, times(2)).save(updatedMovie);
    }

    @Test
    void deleteMovieTestCallsDelete() {
        Movie newMovie = new Movie("newMovie", "Fantasy", 100);
        movieService.createMovie(newMovie);

        Mockito.when(movieRepositoryMock.findByTitle("newMovie")).thenReturn(newMovie);
        movieService.deleteMovieByTitle("newMovie");

        verify(movieRepositoryMock).delete(newMovie);
    }

    @Test
    void findByTitleTestReturnsCorrectMovie() {
        Movie newMovie = new Movie("newMovie", "Fantasy", 100);
        movieService.createMovie(newMovie);

        Mockito.when(movieRepositoryMock.findByTitle("newMovie")).thenReturn(newMovie);

        Movie createdMovie = movieService.findByTitle("newMovie");

        assertEquals("newMovie", createdMovie.getTitle());
        assertEquals("Fantasy", createdMovie.getGenre());
        assertEquals(100, createdMovie.getLength());
    }
}
