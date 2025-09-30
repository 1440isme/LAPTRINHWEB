package vn.binh.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "category")
@NamedQuery(name = "Category.findAll", query = "SELECT DISTINCT c FROM Category_23110184 c LEFT JOIN FETCH c.products")
public class Category_23110184 implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private Integer categoryId;
    @Column(name = "categoryName", columnDefinition = "varchar(200)")
    private String categoryName;
    @Column(name = "status", columnDefinition = "int")
    private Integer status;
    @Column(name = "images", columnDefinition = "varchar(500)")
    private String images;

    // Quan hệ 1-N với Product
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Product_23110184> products;

}