package com.barretoareias.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barretoareias.model.bean.Author;

public class AuthorDAO {
    private Database database = new Database();

    public List<Author> search(){
        final var connection = database.connect();

        try{
            var query = "SELECT * FROM Author";
        
            var statement = connection.prepareStatement(query);

            var result = statement.executeQuery();

            final var list = new ArrayList<Author>();

            if(result == null) return list;

            while(result.next()){
                final var author = new Author(result.getInt("Id"), result.getString("Name"),null);
                list.add(author);
            }

            return list;

        }catch(SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return null;
    }

    public Author addAuthor(Author author){
        final var connection = database.connect();

        try{
            var query = "INSERT INTO Author (Name) VALUES (?)";

            var statement = connection.prepareStatement(query);

            statement.setString(1, author.getName());

            var result = statement.executeUpdate();

            return result > 0 ? author : null;

        }catch(SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return null;
    }
}
