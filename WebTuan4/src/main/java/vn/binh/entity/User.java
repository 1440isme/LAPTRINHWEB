package vn.binh.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT c FROM User c")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "username", columnDefinition = "nvarchar(200)")
	private String name;

	@Column(name = "password", columnDefinition = "nvarchar(200)")
	private String password;

	@Column(name = "fullname", columnDefinition = "nvarchar(200)")
	private String fullname;

	@Column(name = "phone", columnDefinition = "nvarchar(200)")
	private String phone;

	@Column(name = "images", columnDefinition = "NVARCHAR(200)")
	private String images;

	@Column(name = "role", columnDefinition = "INT")
	private int role;
}