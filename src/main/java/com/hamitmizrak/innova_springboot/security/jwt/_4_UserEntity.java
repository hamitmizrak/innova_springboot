package com.hamitmizrak.innova_springboot.security.jwt;

import jakarta.persistence.*;
import lombok.Data;

// LOMBOK
@Data

// Entity
@Entity(name = "Userses")
@Table(name="userses")
public class _4_UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Enum'ı String olarak saklar
    @Column(nullable = false)
    private _3_Role  role; // "USER", "ADMIN" gibi roller

    // Default olarak USER atanır
    @PrePersist
    public void setDefaultRole() {
        if (this.role == null) {
            this.role = _3_Role.USER;
        }
    }
}