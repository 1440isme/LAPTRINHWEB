package vn.binh.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cart")
@NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart_23110184 c")
public class Cart_23110184 implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    private Integer cartId;
    @Column(name = "userId", columnDefinition = "int")
    private Integer userId;
    @Column(name = "buyDate", columnDefinition = "datetime")
    private LocalDateTime buyDate;
    @Column(name = "status", columnDefinition = "int")
    private Integer status;

    // Quan hệ N-1 với User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User_23110184 user;

    // Quan hệ 1-N với CartItem
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartItem_23110184> cartItems;

}
