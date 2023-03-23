package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.repositories.UserRepository;
import by.shuppa.zoomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        if (userService.getMarketUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("user", userService.getMarketUserByPrincipal(principal));
            log.info("logged as {}", userService.getMarketUserByPrincipal(principal).getUsername());
        } else {
            model.addAttribute("message", "Неверный логин или пароль");
        }
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(MarketUser marketUser, Model model) {
        if (userRepository.findByUsername(marketUser.getUsername()) != null) {
            model.addAttribute("message", "Такой логин уже есть в системе.");
            return "/registration";
        }
        if (userService.createUser(marketUser)) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Неверный ввод");
        }
        return "/registration";
    }
}
