package by.shuppa.zoomarket.controllers;

import by.shuppa.zoomarket.models.ProductImage;
import by.shuppa.zoomarket.repositories.ProductImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequiredArgsConstructor
public class ProductImageController {

    private final ProductImageRepository productImageRepository;

    @GetMapping("/product/image/{id}")
    private ResponseEntity <?> getImageById(@PathVariable Long id) {
        ProductImage productImage = productImageRepository.findById(id).orElseThrow();
        return ResponseEntity.ok()
                .header("fileName", productImage.getOriginalFileName())
                .contentType(MediaType.valueOf(productImage.getContentType()))
                .contentLength(productImage.getFileSize())
                .body(new InputStreamResource(new ByteArrayInputStream(productImage.getBytes())));
    }
}
