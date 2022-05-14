package com.barretoareias.model.bean;

public class User {
    
    private int id;
    private String name;
    private String email;
    private String password;
    private UserType type;

    public User(){}

    public User(int id){
        this.id = id;
    }

    public User(String name, String email, String password, UserType type){
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public User(int id, String name, String email, String password, UserType type){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("User {id=%s, name=%s, email=%s, password=%s, type=%s}", this.id,this.name,this.email,this.password,this.type);
    }

}
