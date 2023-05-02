package by.shuppa.zoomarket.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "nvarchar(255)")
    private String name;
    @Column(columnDefinition = "nvarchar(255)")
    private String originalFileName;
    private Long fileSize;
    private String contentType;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] bytes;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Product product;
    private boolean isMainImage;
}
