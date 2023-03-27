package by.shuppa.zoomarket.services;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.models.UserRole;
import by.shuppa.zoomarket.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(MarketUser user, boolean isAdmin) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnable(true);
        if (isAdmin) {
            user.getUserRoles().add(UserRole.ADMIN);
        } else {
            user.getUserRoles().add(UserRole.USER);
        }
        userRepository.save(user);
        log.info("Save new marketUser {}", user.getUsername());
        return true;
    }

    public MarketUser getMarketUserByPrincipal(Principal principal) {
        if (principal != null) {
            log.info("Entrance as {}", principal.getName());
            return userRepository.findByUsername(principal.getName());
        }
        log.info("Not entranced");
        return new MarketUser();
    }

    public UserRole getMarketUserRole(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .getUserRoles()
                .stream()
                .findFirst()
                .orElseThrow();
    }

}
