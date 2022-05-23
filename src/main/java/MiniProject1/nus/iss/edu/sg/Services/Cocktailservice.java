package MiniProject1.nus.iss.edu.sg.Services;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import MiniProject1.nus.iss.edu.sg.Model.Cocktail;
import MiniProject1.nus.iss.edu.sg.Model.Ingredient;

@Service
public class Cocktailservice {



    public List<Ingredient> searchIngredient(String name) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");
        httpHeaders.set("X-RapidAPI-Key", "8a33841533msh9ec79b310943283p1fa0fejsn04e0280ebd11");

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange("https://the-cocktail-db.p.rapidapi.com/search.php?i=" + name, HttpMethod.GET, entity,
                        String.class);

        System.out.println(response.getBody());

        try {
            JsonElement root = new Gson().fromJson(response.getBody(), JsonElement.class);
            JsonArray array = root.getAsJsonObject().get("ingredients").getAsJsonArray();
            List<Ingredient> ingredients = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                Ingredient ingredient = new Ingredient();

                JsonElement element = array.get(i);
                ingredient.setIdIngredient(element.getAsJsonObject().get("idIngredient").getAsString());
                ingredient.setStrIngredient(element.getAsJsonObject().get("strIngredient").getAsString());
                ingredient.setStrDescription(element.getAsJsonObject().get("strDescription").getAsString());
                ingredient.setStrType(element.getAsJsonObject().get("strType").getAsString());
                ingredient.setStrAlcohol(element.getAsJsonObject().get("strAlcohol").getAsString());
                ingredient.setStrABV(element.getAsJsonObject().get("strABV").getAsString());

                ingredients.add(ingredient);
            }

            return ingredients;
        } catch (Exception e) {
            return null;
        }

    }



    public List<Cocktail> search(String name) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("X-RapidAPI-Host", "the-cocktail-db.p.rapidapi.com");
        httpHeaders.set("X-RapidAPI-Key", "8a33841533msh9ec79b310943283p1fa0fejsn04e0280ebd11");

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange("https://the-cocktail-db.p.rapidapi.com/search.php?s=" + name, HttpMethod.GET, entity,
                        String.class);

    

        try {
            JsonElement root = new Gson().fromJson(response.getBody(), JsonElement.class);
            JsonArray array = root.getAsJsonObject().get("drinks").getAsJsonArray();
            System.out.println(array.size());
            List<Cocktail> cocktails = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                Cocktail cocktail = new Cocktail();

                JsonElement element = array.get(i);
                cocktail.setStrDrink(element.getAsJsonObject().get("strDrink").getAsString());
                cocktail.setStrCategory(element.getAsJsonObject().get("strCategory").getAsString());
                //cocktail.setStrIBA(element.getAsJsonObject().get("strIBA").getAsString());
                cocktail.setStrInstructions(element.getAsJsonObject().get("strInstructions").getAsString());
                cocktail.setStrDrinkThumb(element.getAsJsonObject().get("strDrinkThumb").getAsString());
            
                cocktails.add(cocktail);
            }

            return cocktails;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

}
