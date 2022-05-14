package com.barretoareias.model.bean;

public enum UserType {
    Undefined(0),
    Admin(1),
    Student(2);

    private int value;

    UserType(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
