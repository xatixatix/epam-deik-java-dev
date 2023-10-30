package com.epam.training.ticketservice.core.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private Permission permission;

    public MyUser(String name, String password, Permission permission) {
        this.name = name;
        this.password = password;
        this.permission = permission;
    }

    public enum Permission {
        ADMIN,
        USER
    }
}
