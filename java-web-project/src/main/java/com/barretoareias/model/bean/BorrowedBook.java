package com.barretoareias.model.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowedBook {
    private Book book;
    private User user;
    private Date date;

    public BorrowedBook(Book book, User user, Date date) {
        this.book = book;
        this.user = user;
        this.date = date;
    }
}
