package by.shuppa.zoomarket.services;

import by.shuppa.zoomarket.models.Product;
import by.shuppa.zoomarket.models.ProductImage;
import by.shuppa.zoomarket.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product,
                           MultipartFile firstImage,
                           MultipartFile secondImage,
                           MultipartFile thirdImage) {
        Product temporaryProduct = productRepository.save(product);
        addImage(temporaryProduct, firstImage);
        addImage(temporaryProduct, secondImage);
        addImage(temporaryProduct, thirdImage);
        temporaryProduct = productRepository.save(temporaryProduct);
        temporaryProduct.setIdOfMainImage(temporaryProduct.getProductImages().get(0).getId());
        productRepository.save(temporaryProduct);
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
