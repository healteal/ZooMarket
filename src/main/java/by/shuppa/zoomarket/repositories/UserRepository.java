package by.shuppa.zoomarket.repositories;

import by.shuppa.zoomarket.models.MarketUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MarketUser, Long> {
    MarketUser findByUsername(String username);
}
