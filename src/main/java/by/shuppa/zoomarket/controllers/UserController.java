package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.repositories.UserRepository;
import by.shuppa.zoomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(MarketUser marketUser, Model model) {
        if(userRepository.findByUsername(marketUser.getUsername()) != null) {
            model.addAttribute("message", "Такой псевдоним уже есть в системе.");
            return "/registration";
        }
        userService.createUser(marketUser);
        return "redirect:/login";
    }
}
