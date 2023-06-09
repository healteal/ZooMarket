package by.shuppa.zoomarket.services;

import by.shuppa.zoomarket.models.MarketUser;
import by.shuppa.zoomarket.models.Product;
import by.shuppa.zoomarket.models.ProductImage;
import by.shuppa.zoomarket.models.TypeOfProduct;
import by.shuppa.zoomarket.repositories.ProductRepository;
import by.shuppa.zoomarket.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Principal principal,
                           Product product,
                           MultipartFile firstImage,
                           MultipartFile secondImage,
                           MultipartFile thirdImage,
                           String typeOfProduct) {
        product.setMarketUser(getUserByPrincipal(principal));
        if (product.getDescription().length() > 21) {
            product.setShortDescription(product.getDescription().substring(0, 20) + "...");
        } else {
            product.setShortDescription(product.getDescription());
        }
        addImage(product, firstImage);
        addImage(product, secondImage);
        addImage(product, thirdImage);
        setTypeOfProduct(product, typeOfProduct);
        Product temporaryProduct = productRepository.save(product);
        temporaryProduct.setIdOfMainImage(temporaryProduct.getProductImages().get(0).getId());
        productRepository.save(temporaryProduct);
    }

    private static void setTypeOfProduct(Product product, String typeOfProduct) {
        if (typeOfProduct.equals("Питомец")) {
            product.setTypeOfProduct(TypeOfProduct.PET);
        } else if (typeOfProduct.equals("Еда")) {
            product.setTypeOfProduct(TypeOfProduct.FEED);
        } else {
            product.setTypeOfProduct(TypeOfProduct.EQUIPMENT);
        }
    }

    public MarketUser getUserByPrincipal(Principal principal) {
        if (principal != null) {
            return userRepository.findByUsername(principal.getName());
        }
        return new MarketUser();
    }

    private void addImage(Product product, MultipartFile file) {
        if (file.getSize() != 0) {
            product.addProductImage(fileToImage(file));
            if (product.getProductImages().size() == 1) {
                product.getProductImages().get(0).setMainImage(true);
            }
        }
    }

    private ProductImage fileToImage(MultipartFile file) {
        ProductImage productImage = new ProductImage();
        productImage.setName(file.getName());
        productImage.setContentType(file.getContentType());
        productImage.setOriginalFileName(file.getOriginalFilename());
        try {
            productImage.setBytes(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productImage.setFileSize(file.getSize());
        return productImage;
    }
}
