package by.shuppa.zoomarket.repositories;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<MarketUser, Long> {
    MarketUser findByUsername(String username);
    List<MarketUser> findByUserRolesContains(UserRole userRole);
}
