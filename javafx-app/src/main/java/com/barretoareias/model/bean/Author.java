package com.barretoareias.model.bean;

import java.util.List;

public class Author {
    private int id;
    private String name;
    private List<Book> books;

    public Author(int id){
        this.id = id;
    }

    public Author(String name){
        this.name = name;
    }

    public Author(String name,List<Book> books){
        this.name = name;
        this.books = books;
    }

    public Author(int id,String name,List<Book> books){
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

    @Override
    public String toString() {
        return this.name;
    }
}
