package com.barretoareias.controller;
import java.util.stream.Collectors;

import com.barretoareias.App;
import com.barretoareias.model.bean.User;
import com.barretoareias.model.bean.UserType;
import com.barretoareias.model.bean.tableView.BorrowedBookTableView;
import com.barretoareias.model.dao.BorrowedBookDAO;
import com.barretoareias.utils.DataKey;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class MainController {
    
    private User user;
    private BorrowedBookDAO dao = new BorrowedBookDAO();

    @FXML private Button btnAddBook;
    @FXML private Button btnAddAuthor;
    @FXML private TableColumn<BorrowedBookTableView, String> clTitle;
    @FXML private TableColumn<BorrowedBookTableView, String> clAuthor;
    @FXML private TableColumn<BorrowedBookTableView, String> clCategory;
    @FXML private TableColumn<BorrowedBookTableView, String> clWhen;
    @FXML private TableView<BorrowedBookTableView> tblBorrowedBooks;
    @FXML private Text lblMessage;
    
    @FXML
    private void initialize(){
        this.initUser();
        this.initBooksTableView();
    }

    @FXML
    private void onSearchBooks() {
        App.setRoot("search-books");
    }

    @FXML
    private void onReturnBook() {

        final var book = tblBorrowedBooks.getSelectionModel().getSelectedItem();

        if(book == null){
            this.lblMessage.setText("No book selected");
            return;
        }

        if(dao.returnBook(book.getBookId())){
            this.lblMessage.setText("Book returned successfully");
            this.loadTable();
        }
        else
            this.lblMessage.setText("Error while returning book");
    }

    @FXML
    private void onAddAuthor() {
        App.setRoot("author");
    }

    @FXML
    private void onAddBook() {
        App.setRoot("book");
    }

    @FXML
    private void onLogout() {
        App.removeData(DataKey.LoggedUser);
        App.setRoot("login");
    }

    private void initUser(){
        this.user = App.getData(DataKey.LoggedUser);
        if(user.getType() != UserType.Admin){
            btnAddAuthor.setVisible(false);
            btnAddBook.setVisible(false);
        }
    }

    private void initBooksTableView() {
        clTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
        clAuthor.setCellValueFactory(new PropertyValueFactory<>("Author"));
        clCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        clWhen.setCellValueFactory(new PropertyValueFactory<>("When"));

        this.loadTable();
    }

    private void loadTable(){
        final var list = dao.search(user);

        final var listBorrowedBookTableView = list.stream().map(BorrowedBookTableView::new).collect(Collectors.toList());

        var listT = FXCollections.observableList(listBorrowedBookTableView);
        tblBorrowedBooks.setItems(listT);
    }
}
