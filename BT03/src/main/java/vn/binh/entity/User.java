package vn.binh.entity;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@NamedQuery(name = "User.findAll", query = "SELECT c FROM User c")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", columnDefinition = "varchar(50)")
	private String email;

	@Column(name = "passwd", columnDefinition = "varchar(32)")
	private String password;

	@Column(name = "fullname", columnDefinition = "nvarchar(50)")
	private String fullname;

	@Column(name = "phone")
	private Integer phone;
	@Column(name = "signupDate")
	private LocalDateTime signupDate;
	@Column(name = "lastLogin")
	private LocalDateTime lastLogin;
	@Column(name = "isAdmin")
	private Boolean isAdmin;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Rating> ratings = new ArrayList<>();

}