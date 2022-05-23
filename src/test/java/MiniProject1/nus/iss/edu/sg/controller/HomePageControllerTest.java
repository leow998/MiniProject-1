package MiniProject1.nus.iss.edu.sg.controller;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import MiniProject1.nus.iss.edu.sg.Controller.HomepageController;
import MiniProject1.nus.iss.edu.sg.Model.Cocktail;
import MiniProject1.nus.iss.edu.sg.Model.Search;
import MiniProject1.nus.iss.edu.sg.Services.Cocktailservice;

public class HomePageControllerTest {
    
    @Mock
    private Cocktailservice cocktailservice;

    private HomepageController homepageController;

    @BeforeEach
    public void initalize() {
        MockitoAnnotations.openMocks(this);
        homepageController = new HomepageController(cocktailservice);
    }


    @Test
    public void testSearchWhenNoContent() {
        Mockito.when(cocktailservice.search(any())).thenReturn(null);
        Search search = new Search();
        Model model = Mockito.mock(Model.class);

        homepageController.search(search, model);

        Mockito.verify(model).addAttribute("message", "No cocktails found");
    }


    @Test
    public void testSearch() {
        Mockito.when(cocktailservice.search(any())).thenReturn(List.of(new Cocktail()));
        Search search = new Search();
        Model model = Mockito.mock(Model.class);

        homepageController.search(search, model);

        Mockito.verify(model).addAttribute("show", true);
    }

}
