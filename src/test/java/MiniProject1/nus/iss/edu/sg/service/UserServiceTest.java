package MiniProject1.nus.iss.edu.sg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import MiniProject1.nus.iss.edu.sg.Model.User;
import MiniProject1.nus.iss.edu.sg.Repository.UserRepository;
import MiniProject1.nus.iss.edu.sg.Services.UserService;

public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void initalize() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        String output = userService.registerUser(user);
        assertEquals("Username already exists", output);
    }

    @Test
    public void testRegisterUserWhenUsernameIsnotPresent() {
        User user = new User();
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        String output = userService.registerUser(user);
        assertEquals("Success!", output);
    }


    @Test
    public void testLogin() {
        User user = new User();
        Mockito.when(userRepository.findByUsernameAndPassword(any(), any())).thenReturn(Optional.of(user));

        User output = userService.login("test", "test");
        assertEquals(user, output);
    }


    @Test
    public void testLoginWhenUserNameAndPasswordIsWrong() {
        User user = new User();
        Mockito.when(userRepository.findByUsernameAndPassword(any(), any())).thenReturn(Optional.empty());

        User output = userService.login("test", "test");
        assertEquals(null, output);
    }
}
