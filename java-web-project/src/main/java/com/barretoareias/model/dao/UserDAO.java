package com.barretoareias.model.dao;

import java.sql.SQLException;
import java.sql.Statement;

import com.barretoareias.model.bean.User;
import com.barretoareias.model.bean.UserType;

public class UserDAO {
    
    private Database database = new Database();

    public User loginUser(String email, String password) {
        var connection = database.connect();

        try {

            var query = "SELECT * FROM User WHERE Email=? AND PASSWORD=?";

            var statement = connection.prepareStatement(query);

            statement.setString(1,email);

            statement.setString(2,password);

            var result = statement.executeQuery();

            if(result == null || !result.next()) return null;


            var user = new User(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("email"),
                            result.getString("password"),
                            UserType.values()[result.getInt("type")]
                        );

            return user;
        } catch (SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return null;
    }

    public boolean doesEmailExists(String email){
        var connection = database.connect();

        try {
            var query = "SELECT Email FROM User WHERE Email=?";

            var statement = connection.prepareStatement(query);

            statement.setString(1,email);

            var result = statement.executeQuery();

            return result != null && result.next();

        }catch (SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }
        return false;
    }

    public User registerUser(User user){
        var connection = database.connect();

        try{
            var query = "INSERT INTO User (Name,Email,Password,Type) VALUES (?,?,?,?)";

            var statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4,  user.getType().getValue());

            var result = statement.executeUpdate();

            if(result > 0){
                var keyResult = statement.getGeneratedKeys();
                keyResult.next();
                user.setId(keyResult.getInt(1));
                return user;
            }

        }catch(SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return null;
    }

}
