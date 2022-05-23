package MiniProject1.nus.iss.edu.sg.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import MiniProject1.nus.iss.edu.sg.Model.Cocktail;
import MiniProject1.nus.iss.edu.sg.Model.Search;
import MiniProject1.nus.iss.edu.sg.Services.Cocktailservice;

@Controller
public class HomepageController {

  
    private Cocktailservice cocktailservice;

    @Autowired
    public HomepageController(Cocktailservice cocktailservice) {
        this.cocktailservice = cocktailservice;
    }

    @PostMapping("/search")
    public String search(@ModelAttribute("search") Search search, Model model) {
        List<Cocktail> cocktailList = cocktailservice.search(search.getDrink());

        if (cocktailList == null) {
            model.addAttribute("search", search);
            model.addAttribute("show", false);
            model.addAttribute("message", "No cocktails found");
        } else {
            model.addAttribute("search", search);
            model.addAttribute("show", true);
            model.addAttribute("cocktails", cocktailList);
        }
        return "home";
    }

    @PostMapping("/searchIngredient")
    public String searchIngredient(@ModelAttribute("search") Search search, Model model) {
        List<Cocktail> cocktailList = cocktailservice.search(search.getDrink());

        if (cocktailList == null) {
            model.addAttribute("search", search);
            model.addAttribute("show", false);
            model.addAttribute("message", "No cocktails found");
        } else {
            model.addAttribute("search", search);
            model.addAttribute("show", true);
            model.addAttribute("cocktails", cocktailList);
        }
        return "home";
    }

}
