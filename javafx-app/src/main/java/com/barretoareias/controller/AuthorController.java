package com.barretoareias.controller;

import com.barretoareias.App;
import com.barretoareias.model.bean.Author;
import com.barretoareias.model.dao.AuthorDAO;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AuthorController {

    private AuthorDAO dao = new AuthorDAO();

    @FXML private Text lblMessage;
    @FXML private TextField txtName;

    @FXML
    private void onAddAuthor(){
        if(txtName.getText().isEmpty()){
            lblMessage.setText("Name is required");
            return;
        }

        if(dao.addAuthor(new Author(txtName.getText())) != null){
            lblMessage.setText("Author added successfully");
            txtName.clear();
        }
        else
            lblMessage.setText("Error while adding author");
    }

    @FXML
    private void onBack(){
        App.setRoot("main");
    }
    
}
