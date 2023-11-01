package user;

import com.epam.training.ticketservice.core.user.MyUserService;
import com.epam.training.ticketservice.core.user.entity.MyUser;
import com.epam.training.ticketservice.core.user.impl.MyUserServiceImpl;
import com.epam.training.ticketservice.core.user.repository.MyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

class MyUserServiceTest {

    @Mock
    private MyUserRepository myUserRepository;
    private MyUserService myUserService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        myUserService = new MyUserServiceImpl(myUserRepository);
    }

    @Test
    void registerCallsSave() {
        MyUser newUser = new MyUser("newName", "newPassword", MyUser.Permission.USER);
        myUserService.register(newUser);

        verify(myUserRepository).save(newUser);
    }

    @Test
    void loginReturnsUserIfExists() {
        MyUser newUser = new MyUser("newName", "newPassword", MyUser.Permission.USER);
        myUserService.register(newUser);

        Mockito.when(myUserRepository
                .findByNameAndPassword("newName", "newPassword")).thenReturn(Optional.of(newUser));

        Optional<MyUser> existingUser =
                myUserService.login("newName", "newPassword");

        assertTrue(existingUser.isPresent());
    }

    @Test
    void loginReturnsEmptyIfUserDoesNotExist() {
        Optional<MyUser> notExistingUser =
                myUserService.login("userThatDoesNotExits", "notExistingPassword");

        assertTrue(notExistingUser.isEmpty());
    }

    @Test
    void describeReturnsNotSignedInIfNotSignedIn() {
        assertEquals("You are not signed in", myUserService.describe());
    }

    @Test
    void describeReturnsSignedInWithAccountNameIfSignedInAsUser() {
        MyUser newUser = new MyUser("newName", "newPassword", MyUser.Permission.USER);
        myUserService.register(newUser);

        Mockito.when(myUserRepository
                .findByNameAndPassword("newName", "newPassword")).thenReturn(Optional.of(newUser));

        myUserService.login("newName", "newPassword");

        assertEquals("Signed in with account 'newName'", myUserService.describe());
    }

    @Test
    void describeReturnsSignedInWithPrivilegedAccountNameIfSignedInAsAdmin() {
        MyUser newUser = new MyUser("newName", "newPassword", MyUser.Permission.ADMIN);
        myUserService.register(newUser);

        Mockito.when(myUserRepository
                .findByNameAndPassword("newName", "newPassword")).thenReturn(Optional.of(newUser));

        myUserService.login("newName", "newPassword");

        assertEquals("Signed in with privileged account 'newName'", myUserService.describe());
    }
}
