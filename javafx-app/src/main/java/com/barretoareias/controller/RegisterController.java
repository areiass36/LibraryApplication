package com.barretoareias.controller;

import java.io.IOException;

import com.barretoareias.App;
import com.barretoareias.model.bean.User;
import com.barretoareias.model.bean.UserType;
import com.barretoareias.model.dao.UserDAO;
import com.barretoareias.utils.DataKey;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class RegisterController {
    
    private UserDAO dao;

    public RegisterController(){
        dao = new UserDAO();
    }

    @FXML private TextField txtName;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPassword;
    @FXML private TextField txtPasswordConfirm;
    @FXML private Text lblMessage;

    @FXML
    private void onRegister() throws IOException{
        final var user = new User(txtName.getText(),txtEmail.getText(),txtPassword.getText(),UserType.Student);

        if(!isValidUser(user,txtPasswordConfirm.getText())) return;

        if(dao.doesEmailExists(user.getEmail())){
            lblMessage.setText("This email is already being used!");
            return;
        }

        var result = dao.registerUser(user);

        if(result != null){
            App.setData(DataKey.LoggedUser,result);
            App.setRoot("main");
        } 
    }

    private boolean isValidUser(User user, String confirmPassword) {
        if(user.getName() == "" || user.getEmail() == "" || user.getPassword() == ""){
            lblMessage.setText("All fields are required!");
            return false;
        }

        if(!user.getEmail().contains("@fatec.sp.gov.br")){
            lblMessage.setText("Not a valid Fatec email!");
            return false;
        }

        if(!user.getPassword().equals(confirmPassword)){
            lblMessage.setText("Password does not match");
            return false;
        }

        return true;
    }
}
