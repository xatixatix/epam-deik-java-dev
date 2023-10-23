package com.epam.training.ticketservice.core.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private PERMISSION permission;

    public User(String name, String password, PERMISSION permission) {
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    public enum PERMISSION {
        ADMIN,
        USER
    }
}
