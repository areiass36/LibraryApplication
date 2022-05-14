package com.barretoareias.controller;

import java.util.ArrayList;

import com.barretoareias.App;
import com.barretoareias.model.bean.Author;
import com.barretoareias.model.bean.Book;
import com.barretoareias.model.bean.BookCategory;
import com.barretoareias.model.dao.AuthorDAO;
import com.barretoareias.model.dao.BookDAO;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BookController {

    private BookDAO dao = new BookDAO();
    private AuthorDAO authorDao = new AuthorDAO();

    @FXML private TextField txtTitle;
    @FXML private ComboBox<Author> cmbAuthor;
    @FXML private Text lblMessage;
    @FXML private ComboBox<String> cmbCategory;

    @FXML
    private void onBack() {
        App.setRoot("main");
    }

    @FXML
    private void initialize(){
        this.initAuthorsComboBox();
        this.initCategoryComboBox();
    }

    @FXML
    private void onAddBook(){
        final var book = validBook();

        if(book != null){
            dao.addBook(book);
            lblMessage.setText("Book added successfully!");
        }
    }

    private Book validBook(){
        var message = "";

        if(txtTitle.getText().isEmpty())
            message = "Title is required";

        if(cmbAuthor.getValue() == null)
            message = "Author is required";

        if(cmbCategory.getValue() == null)
            message = "Category is required";

        return message != "" ? null : new Book(txtTitle.getText(), BookCategory.valueOf(cmbCategory.getValue()), cmbAuthor.getValue());
    }

    private void initAuthorsComboBox(){
        var list = new ArrayList<Author>();
        list.addAll(authorDao.search());
        cmbAuthor.setItems(FXCollections.observableList(list));
    }

    private void initCategoryComboBox(){
        final var categories = new ArrayList<String>();

        for(final var c : BookCategory.values()){
            if(c != BookCategory.Undefined)
                categories.add(c.toString());
        }
        
        cmbCategory.setItems(FXCollections.observableArrayList(categories));
    }
    
}
