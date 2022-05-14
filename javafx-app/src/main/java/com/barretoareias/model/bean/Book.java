package com.barretoareias.model.bean;

public class Book {
    private int id;
    private String title;
    private BookCategory category;
    private Author author;

    public Book(int id){
        this.id = id;
    }

    public Book(String title, BookCategory category, Author author){
        this.title = title;
        this.category = category;
        this.author = author;
    }

    public Book(int id,String title, BookCategory category, Author author){
        this.id = id;
        this.title = title;
        this.category = category;
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
