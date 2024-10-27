package com.pucpr.chestplace.features.user.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.pucpr.chestplace.features.user.enums.Role;

@Entity
@Table(name = "USUARIOS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    @Column(unique = true)
    private String email;
    @Setter
    @NotBlank
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private Role role;

    public User(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void setUsername(String username) {
        this.email = username;
    }

}
