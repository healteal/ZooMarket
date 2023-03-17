package by.shuppa.zoomarket.services;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.models.UserRole;
import by.shuppa.zoomarket.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(MarketUser user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnable(true);
        user.getUserRoles().add(UserRole.USER);
        userRepository.save(user);
        return true;
    }
}
