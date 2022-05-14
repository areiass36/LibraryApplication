package com.barretoareias.model.bean;

public enum BookCategory {
    Undefined(0),
    Adventure(1),
    Juvenile(2),
    Fiction(3),
    Romance(4),
    Horror(5),
    Action(6),
    Classic(7),
    Fantasy(8);

    private int value;

    BookCategory(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
