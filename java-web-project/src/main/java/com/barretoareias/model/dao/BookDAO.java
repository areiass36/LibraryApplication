package com.barretoareias.model.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.barretoareias.model.bean.Author;
import com.barretoareias.model.bean.Book;
import com.barretoareias.model.bean.BookCategory;

public class BookDAO {

    private Database database = new Database();

    public List<Book> searchBooks(String title, String authorName, String available, BookCategory category) {
        final var connection = database.connect();

        try {
            var query = new StringBuilder();

            query.append("SELECT B.Id IdBook,")
                    .append("B.Title TitleBook,")
                    .append("B.Category CategoryBook,")
                    .append("A.Id IdAuthor,")
                    .append("A.Name NameAuthor ")
                    .append("FROM Book B ")
                    .append("INNER JOIN Author A ON A.Id = B.IdAuthor ")
                    .append("WHERE 1=1");

            if (!title.equals(""))
                query.append(" AND B.Title LIKE '%" + title + "%'");

            if (!authorName.equals(""))
                query.append(" AND A.Name = '" + authorName + "'");

            if (category != BookCategory.Undefined)
                query.append(" AND B.Category = " + category.getValue());

            if (available.equals("Available"))
                query.append(" AND B.Id NOT IN (SELECT IdBook FROM BorrowedBooks)");
            else if (available.equals("Not Available"))
                query.append(" AND B.Id IN (SELECT IdBook FROM BorrowedBooks)");

            var statement = connection.prepareStatement(query.toString());

            var result = statement.executeQuery();

            final var list = new ArrayList<Book>();

            if (result == null)
                return list;

            while (result.next()) {
                final var author = new Author(result.getInt("IdAuthor"), result.getString("NameAuthor"), null);

                final var book = new Book(result.getInt("IdBook"),
                        result.getString("TitleBook"),
                        BookCategory.values()[result.getInt("CategoryBook")],
                        author);

                list.add(book);
            }

            return list;

        } catch (SQLException e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return null;
    }

    public Book addBook(Book book) {
        final var connection = database.connect();

        try {
            var query = "INSERT INTO Book (Title,Category,IdAuthor) VALUES (?,?,?)";

            var statement = connection.prepareStatement(query);

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getCategory().getValue());
            statement.setInt(3, book.getAuthor().getId());

            var result = statement.executeUpdate();

            return result > 0 ? book : null;

        } catch (SQLException e) {
            System.out.printf("%s\n", e.getMessage());
        }

        return null;
    }
}
