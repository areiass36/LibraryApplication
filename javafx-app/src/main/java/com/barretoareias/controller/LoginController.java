package com.barretoareias.controller;
import java.io.IOException;

import com.barretoareias.App;
import com.barretoareias.model.dao.UserDAO;
import com.barretoareias.utils.DataKey;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    private UserDAO userDAO;

    public LoginController(){
        this.userDAO = new UserDAO();
    }

    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;
    @FXML private Text lblMessage;

    @FXML
    private void onRegister() throws IOException {
        App.setRoot("register");
    }

    @FXML
    private void onLogin() throws IOException {
        var email = txtEmail.getText();
        var password =  txtPassword.getText();
        var user = userDAO.loginUser(email, password);

        if(user != null){
            App.setData(DataKey.LoggedUser,user);
            App.setRoot("main");
        }
        else
            lblMessage.setText("Your email or password is not correct!");
    }
}
