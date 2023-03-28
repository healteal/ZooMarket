package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.models.UserRole;
import by.shuppa.zoomarket.repositories.UserRepository;
import by.shuppa.zoomarket.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String login(Principal principal,
                        Model model) {
        if (userService.getMarketUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("user", userService.getMarketUserByPrincipal(principal));
            model.addAttribute("userRole", userService.getMarketUserRole(principal));
        } else {
            model.addAttribute("message", "Неверный логин или пароль");
        }
        return "login";
    }

    @PostMapping("/registration")
    public String createUser(MarketUser marketUser,
                             Model model,
                             @RequestParam(name = "isAdmin", required = false) String admin) {
        if (userRepository.findByUsername(marketUser.getUsername()) != null) {
            model.addAttribute("message", "Такой логин уже есть в системе.");
            return "/registration";
        }
        boolean isAdmin = admin.equals("admin");
        if (userService.createUser(marketUser, isAdmin)) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Неверный ввод");
        }
        return "/registration";
    }

    @GetMapping("/user/{id}")
    public String userPage(@PathVariable Long id,
                           Model model,
                           Principal principal) {
        model.addAttribute("marketUser", userRepository.findById(id).orElseThrow());
        model.addAttribute("products", userRepository.findById(id).orElseThrow().getProducts());
        if (userService.getMarketUserByPrincipal(principal).getUsername() != null) {
            model.addAttribute("user", userService.getMarketUserByPrincipal(principal));
            model.addAttribute("userRole", userService.getMarketUserRole(principal));
        }
        return "user-page";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("listOfUsers", userRepository.findByUserRolesContains(UserRole.USER));
        return "admin-page";
    }

    @PostMapping("/admin/ban/{id}")
    public String banUser(@PathVariable Long id) {
        MarketUser user = userRepository.findById(id).orElseThrow();
        user.setEnable(false);
        userRepository.save(user);
        return "redirect:/admin";
    }

    @PostMapping("/admin/unban/{id}")
    public String unbanUser(@PathVariable Long id) {
        MarketUser user = userRepository.findById(id).orElseThrow();
        user.setEnable(true);
        userRepository.save(user);
        return "redirect:/admin";
    }
}
