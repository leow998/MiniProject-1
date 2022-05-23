package MiniProject1.nus.iss.edu.sg.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import MiniProject1.nus.iss.edu.sg.Model.Cocktail;
import MiniProject1.nus.iss.edu.sg.Model.Ingredient;
import MiniProject1.nus.iss.edu.sg.Services.Cocktailservice;

public class CocktailServiceTest {
    
    @Test
    public void testSearch() {
        Cocktailservice service = new Cocktailservice();
        List<Cocktail> output = service.search("gin");
        assertTrue(output.size() > 0);
    }


    @Test
    public void testSearchIngredient() {
        Cocktailservice service = new Cocktailservice();
        List<Ingredient> output = service.searchIngredient("vodka");
        assertTrue(output.size() > 0);
    }
}
