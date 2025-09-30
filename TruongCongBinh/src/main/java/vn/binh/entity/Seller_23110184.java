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
@Table(name = "seller")
@NamedQuery(name = "Seller.findAll", query = "SELECT s FROM Seller_23110184 s")
public class Seller_23110184 implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sellerId")
    private Integer sellerId;

    @Column(name = "sellerName")
    private String sellerName;
    @Column(name = "status", columnDefinition = "int")
    private Integer status;
    @Column(name = "images", columnDefinition = "varchar(500)")
    private String images;

    // Quan hệ 1-N với Users
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<User_23110184> users;

    // Quan hệ 1-N với Product
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product_23110184> products;

}
