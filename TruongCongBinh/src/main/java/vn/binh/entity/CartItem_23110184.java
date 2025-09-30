package vn.binh.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cartitem")
@NamedQuery(name = "CartItem.findAll", query = "SELECT c FROM CartItem_23110184 c")
public class CartItem_23110184 implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItemId")
    private Integer cartItemId;
    @Column(name = "quantity", columnDefinition = "int")
    private Integer quantity;
    @Column(name = "unitPrice", columnDefinition = "decimal(6,2)")
    private Double unitPrice;

    @Column(name = "productId", columnDefinition = "int")
    private Integer productId;
    @Column(name = "cartId", columnDefinition = "int")
    private Integer cartId;

    // Quan hệ N-1 với Product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product_23110184 product;

    // Quan hệ N-1 với Cart
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId", insertable = false, updatable = false)
    private Cart_23110184 cart;

}
