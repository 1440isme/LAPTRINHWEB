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
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User_23110184 u")
public class User_23110184 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Integer userId;
	@Column(name = "username", columnDefinition = "varchar(50)")
	private String username;
	@Column(name = "email", columnDefinition = "varchar(100)")
	private String email;

	@Column(name = "password", columnDefinition = "varchar(50)")
	private String password;

	@Column(name = "fullname", columnDefinition = "nvarchar(50)")
	private String fullname;
	@Column(name = "images", columnDefinition = "varchar(500)")
	private String images;
	@Column(name = "phone")
	private Integer phone;
	@Column(name = "status", columnDefinition = "int")
	private Integer status;
	@Column(name = "code", columnDefinition = "varchar(50)")
	private String code;
	@Column(name = "roleId", columnDefinition = "int")
	private Integer roleId;
	@Column(name = "sellerId", columnDefinition = "int")
	private Integer sellerId;

	// Quan hệ N-1 với UserRoles
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", insertable = false, updatable = false)
	private UserRoles_23110184 userRole;

	// Quan hệ N-1 với Seller
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sellerId", insertable = false, updatable = false)
	private Seller_23110184 seller;

	// Quan hệ 1-N với Cart
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Cart_23110184> carts;

}