package com.barretoareias.utils;

public enum DataKey {
    LoggedUser("loggedUser");

    private String value;

    DataKey(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
