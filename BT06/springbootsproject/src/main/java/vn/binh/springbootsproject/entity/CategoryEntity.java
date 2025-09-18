package vn.binh.springbootsproject.entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "Categories")
public class CategoryEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(name = "categoryName", columnDefinition = "nvarchar(200)")
    private String name;



    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<ProductEntity> product;

}