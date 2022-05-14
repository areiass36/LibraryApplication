package com.barretoareias.model.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.barretoareias.model.bean.Author;
import com.barretoareias.model.bean.Book;
import com.barretoareias.model.bean.BookCategory;
import com.barretoareias.model.bean.BorrowedBook;
import com.barretoareias.model.bean.User;

public class BorrowedBookDAO {
    
    private Database database = new Database();

    public List<BorrowedBook> search(User user){
        final var connection = database.connect();

        try{

            var query = new StringBuilder()
                 .append("SELECT B.Id IdBook,")
                 .append("B.Title TitleBook,")
                 .append("B.Category CategoryBook,")
                 .append("BB.Date DateBorrow,")
                 .append("A.Id IdAuthor,")
                 .append("A.Name NameAuthor ")
                 .append("FROM BorrowedBooks BB ")
                 .append("INNER JOIN Book B ON B.Id = BB.IdBook ")
                 .append("INNER JOIN Author A ON A.Id = B.IdAuthor ")
                 .append("WHERE BB.IdUser = ?");
                 
            var statement = connection.prepareStatement(query.toString());

            statement.setInt(1,user.getId());

            var result = statement.executeQuery();
                 
            final var list = new ArrayList<BorrowedBook>();
            
            if(result == null) return list;

            while(result.next()){
                final var author = new Author(result.getInt("IdAuthor"), result.getString("NameAuthor"), null);

                final var book = new Book(result.getInt("IdBook"),
                                    result.getString("TitleBook"),
                                    BookCategory.values()[result.getInt("CategoryBook")],
                                    author);

                final var borrowBook = new BorrowedBook(book, user, result.getDate("DateBorrow"));

                list.add(borrowBook);
            }

            return list;

        }catch(SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return new ArrayList<>();
    }

    public boolean borrowBook(User user, Book book){
        final var connection = database.connect();

        try {

            var query = "INSERT INTO BorrowedBooks (IdUser,IdBook,Date) VALUES (?,?,?)";

            var statement = connection.prepareStatement(query.toString());

            statement.setInt(1,user.getId());
            statement.setInt(2,book.getId());
            statement.setString(3,LocalDateTime.now().toString());

            var result = statement.executeUpdate();

            return result > 0;


        }catch(SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return false;
    }

    public boolean returnBook(int bookId){
        final var connection = database.connect();

        try {

            var query = "DELETE FROM BorrowedBooks WHERE IdBook = ?";

            var statement = connection.prepareStatement(query.toString());

            statement.setInt(1,bookId);

            var result = statement.executeUpdate();

            return result > 0;
        }catch(SQLException e){
            System.out.printf("%s\n",e.getMessage());
        }

        return false;
    }
}
