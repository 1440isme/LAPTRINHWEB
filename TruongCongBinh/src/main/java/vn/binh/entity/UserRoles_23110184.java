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
@Table(name = "userroles")
@NamedQuery(name = "UserRoles.findAll", query = "SELECT ur FROM UserRoles_23110184 ur")
public class UserRoles_23110184 implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Integer roleId;

    @Column(name = "roleName", columnDefinition = "varchar(50)")
    private String roleName;

    // Quan hệ 1-N với Users
    @OneToMany(mappedBy = "userRole", fetch = FetchType.LAZY)
    private List<User_23110184> users;
}
