package by.shuppa.zoomarket.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String description;
    private Double price;
    private Double rating;
    @Column(columnDefinition = "nvarchar(255)")
    private String author;
    private Long idOfMainImage;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();


    public void addProductImage(ProductImage productImage) {
        productImages.add(productImage);
        productImage.setProduct(this);
    }
}
