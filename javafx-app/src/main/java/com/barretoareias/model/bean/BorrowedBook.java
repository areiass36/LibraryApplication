package com.barretoareias.model.bean;

import java.util.Date;

public class BorrowedBook {
    private Book book;
    private User user;
    private Date date;

    public BorrowedBook(Book book,User user,Date date){
        this.book = book;
        this.user = user;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

}
