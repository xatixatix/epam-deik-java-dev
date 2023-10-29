package com.epam.training.ticketservice.core.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MyUser {

    @Id
    @GeneratedValue
    private int id;
    @Column(unique = true)
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private PERMISSION permission;

    public MyUser(String name, String password, PERMISSION permission) {
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    public enum PERMISSION {
        ADMIN,
        USER
    }
}
