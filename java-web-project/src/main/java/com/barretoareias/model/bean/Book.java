package com.barretoareias.model.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private BookCategory category;
    private Author author;

    public Book(int id) {
        this.id = id;
    }

    public Book(String title, BookCategory category, Author author) {
        this.title = title;
        this.category = category;
        this.author = author;
    }

    public Book(int id, String title, BookCategory category, Author author) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.author = author;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
