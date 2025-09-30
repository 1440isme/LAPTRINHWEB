package vn.binh.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "product")
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product_23110184 p")
public class Product_23110184 implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "productId")
	private int productId;

	@Column(name = "productName", columnDefinition = "varchar(200)")
	private String productName;
	@Column(name = "productCode", columnDefinition = "varchar(200)")
	private String productCode;
	@Column(name = "categoryId", columnDefinition = "int")
	private Integer categoryId;
	@Column(name = "description", columnDefinition = "varchar(500)")
	private String description;
	// amount: số lượng
	@Column(name = "amount", columnDefinition = "int")
	private Integer amount;
	// price: giá bán
	@Column(name = "price")
	private Float price;
	@Column(name = "stock", columnDefinition = "int")
	private Integer stock;

	@Column(name = "images", columnDefinition = "varchar(500)")
	private String images;
	@Column(name = "wishlist", columnDefinition = "int")
	private Integer wishlist;
	@Column(name = "status", columnDefinition = "int")
	private Integer status;
	@Column(name = "createdDate", columnDefinition = "date")
	private LocalDate createdDate;
	@Column(name = "sellerId", columnDefinition = "int")
	private Integer sellerId;

	// Quan hệ N-1 với Category
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private Category_23110184 category;

	// Quan hệ N-1 với Seller
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerId", insertable = false, updatable = false)
	private Seller_23110184 seller;

	// Quan hệ 1-N với CartItem
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<CartItem_23110184> cartItems;

}
