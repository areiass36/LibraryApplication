package com.barretoareias.model.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private int id;
    private String name;
    private List<Book> books;

    public Author(int id) {
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Author(int id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

}
