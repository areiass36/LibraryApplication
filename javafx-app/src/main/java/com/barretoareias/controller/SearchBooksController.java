package com.barretoareias.controller;


import java.util.ArrayList;
import java.util.List;

import com.barretoareias.App;
import com.barretoareias.model.bean.Author;
import com.barretoareias.model.bean.Book;
import com.barretoareias.model.bean.BookCategory;
import com.barretoareias.model.dao.AuthorDAO;
import com.barretoareias.model.dao.BookDAO;
import com.barretoareias.model.dao.BorrowedBookDAO;
import com.barretoareias.utils.DataKey;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class SearchBooksController {
    
    private BookDAO bookDao = new BookDAO();
    private BorrowedBookDAO borrowBookDao = new BorrowedBookDAO();
    private AuthorDAO authorDao = new AuthorDAO();

    @FXML private TableView<Book> tblBooks;
    @FXML private TableColumn<Book, String> clTitle;
    @FXML private TableColumn<Book, String> clAuthor;
    @FXML private TableColumn<Book, String> clCategory;
    @FXML private TextField txtTitle;
    @FXML private ComboBox<Author> cmbAuthor;
    @FXML private ComboBox<String> cmbCategory;
    @FXML private ComboBox<String> cmbAvailable;
    @FXML private Text lblMessage;

    @FXML
    private void initialize(){
        this.initAuthorsComboBox();
        this.initCategoryComboBox();
        this.initAvailableComboBox();
        this.initBooksTableView();
    }

    @FXML
    private void onSearch() {
        var listT = FXCollections.observableList(search());
        tblBooks.setItems(listT);
    }

    @FXML
    private void onBack() {
        App.setRoot("main");
    }

    @FXML
    private void onBorrow(){
        final var book = tblBooks.getSelectionModel().getSelectedItem();

        if(book == null){
            this.lblMessage.setText("No book selected");
            return;
        }

        final var success = this.borrowBookDao.borrowBook(App.getData(DataKey.LoggedUser), book);

        if(!success)
            this.lblMessage.setText("Book is already borrowed!");
        else
            this.lblMessage.setText("Book borrowed!");

        this.search();
    }

    private List<Book> search(){
        final var authorName = cmbAuthor.getValue() != null ? cmbAuthor.getValue().getName() : "";

        final var category = cmbCategory.getValue() != null && cmbCategory.getValue() != "" ? cmbCategory.getValue() : "Undefined";

        final var available = cmbAvailable.getValue();

        return bookDao.searchBooks(txtTitle.getText(), authorName, available, BookCategory.valueOf(category));
    }

    private void initBooksTableView(){
        clTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        clAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        clCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));

        var listT = FXCollections.observableList(search());
        tblBooks.setItems(listT);
    }

    private void initAuthorsComboBox(){
        final var authors = new ArrayList<Author>();
        authors.add(new Author("",null));
        authors.addAll(authorDao.search());

        cmbAuthor.setItems(FXCollections.observableArrayList(authors));
    }

    private void initCategoryComboBox(){
        final var categories = new ArrayList<String>();
        categories.add("");

        for(final var c : BookCategory.values()){
            if(c != BookCategory.Undefined)
                categories.add(c.toString());
        }
            

        cmbCategory.setItems(FXCollections.observableArrayList(categories));
    }

    private void initAvailableComboBox(){
        final var categories = new ArrayList<String>();
        categories.add("");
        categories.add("Available");
        categories.add("Not Available");

        cmbAvailable.setItems(FXCollections.observableArrayList(categories));
    }
}
