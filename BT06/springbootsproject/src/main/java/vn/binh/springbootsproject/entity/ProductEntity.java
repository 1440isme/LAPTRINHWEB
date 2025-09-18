package vn.binh.springbootsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "product_name", length = 500, columnDefinition = "nvarchar(500)")
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double unitPrice;
    @Column(length = 200)
    private String images;
    @Column(columnDefinition = "nvarchar(200) not null")
    private String description;
    @Column(nullable = false)
    private double discount;
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(nullable = false)
    private short status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

}
