package MiniProject1.nus.iss.edu.sg.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import MiniProject1.nus.iss.edu.sg.Controller.UserController;
import MiniProject1.nus.iss.edu.sg.Model.User;
import MiniProject1.nus.iss.edu.sg.Services.UserService;

public class UserControllerTest {
    
    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void intialize() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(userService);
    }

    @Test
    public void testRegisterUser() {
        User user = new User();
        Model model = Mockito.mock(Model.class);
        Mockito.when(userService.registerUser(any())).thenReturn("Success!");

        String output = userController.registerUser(user, model);
        assertEquals("login", output);
    }

    @Test
    public void testRegisterUserWhenFailure() {
        User user = new User();
        Model model = Mockito.mock(Model.class);
        Mockito.when(userService.registerUser(any())).thenReturn("fail!");

        String output = userController.registerUser(user, model);
        assertEquals("register", output);
    }


    @Test
    public void testRegisterPage() {
        Model model = Mockito.mock(Model.class);
    
        String output = userController.register(model);
        assertEquals("register", output);
    }

    @Test
    public void testLoginPage() {
        Model model = Mockito.mock(Model.class);
    
        String output = userController.login(model);
        assertEquals("login", output);
    }

    @Test
    public void testLoginUser() {
        User user = new User();
        Model model = Mockito.mock(Model.class);
        Mockito.when(userService.login(any(), any())).thenReturn(new User());

        String output = userController.loginUser(user, model);
        assertEquals("home", output);
    }

    @Test
    public void testLoginUserWhenError() {
        User user = new User();
        Model model = Mockito.mock(Model.class);
        Mockito.when(userService.login(any(), any())).thenReturn(null);

        String output = userController.loginUser(user, model);
        assertEquals("login", output);
    }


}
