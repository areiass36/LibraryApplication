package com.barretoareias.model.bean.tableView;

import com.barretoareias.model.bean.BorrowedBook;

public class BorrowedBookTableView {
    
    private int bookId;
    private int userId;
    private String title;
    private String author;
    private String category;
    private String when;

    public BorrowedBookTableView(int bookId, int userId, String title,String author,String category,String when){
        this.bookId = bookId;
        this.author = author;
        this.category = category;
        this.userId = userId;
        this.title = title;
        this.when = when;
    }

    public BorrowedBookTableView(BorrowedBook book){
        this.bookId = book.getBook().getId();
        this.author = book.getBook().getAuthor().getName();
        this.category = book.getBook().getCategory().name();
        this.userId = book.getUser().getId();
        this.title = book.getBook().getTitle();
        this.when = book.getDate().toString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }
    

}
