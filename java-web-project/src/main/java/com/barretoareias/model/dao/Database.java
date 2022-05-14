package com.barretoareias.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    
    private String conString = "jdbc:mysql://172.17.0.2:3306/JavaFxDb";
    private Connection connection;

    public Connection connect(){
        try {                              
            this.connection = DriverManager.getConnection(conString,"root","pass");
        } catch (SQLException e) {
            System.out.printf("An Error Occured while trying to connect to database\n");
            System.out.printf("%s\n",e.getMessage());
        }
        return connection;
    } 

}
