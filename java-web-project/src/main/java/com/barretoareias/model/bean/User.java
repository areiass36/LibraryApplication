package com.barretoareias.model.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private UserType type;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String name, String email, String password, UserType type) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public User(int id, String name, String email, String password, UserType type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }
}
