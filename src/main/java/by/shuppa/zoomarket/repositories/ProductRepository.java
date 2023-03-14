package by.shuppa.zoomarket.repositories;

import by.shuppa.zoomarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
