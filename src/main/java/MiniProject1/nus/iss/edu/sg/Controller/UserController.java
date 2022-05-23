package MiniProject1.nus.iss.edu.sg.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import MiniProject1.nus.iss.edu.sg.Model.Cocktail;
import MiniProject1.nus.iss.edu.sg.Model.Search;
import MiniProject1.nus.iss.edu.sg.Model.User;
import MiniProject1.nus.iss.edu.sg.Services.UserService;

@Controller

public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model){
        String output = userService.registerUser(user);
        if (output.equals("Success!")){
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", false);
            return "login";
        }
        else{
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", true);
            model.addAttribute("message", output);
            return "register";

        }


    }
    @GetMapping("/")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("errorMessage", false);
        return "login";
    }
    @GetMapping("/registerPage")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("errorMessage", false);
        model.addAttribute("message", "");
        return "register";
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model){
        User usr = userService.login(user.getUsername(), user.getPassword());
        if (usr == null){
            model.addAttribute("user", user);
            model.addAttribute("errorMessage", true);
            return "login";
        }
        else{
            List<Cocktail> cocktailList = new ArrayList<>();
            model.addAttribute("search", new Search());
            model.addAttribute("show", false);
            model.addAttribute("message", "Search cocktails");
            model.addAttribute("cocktails", cocktailList);
            return "home";
        }
        
    }


}
