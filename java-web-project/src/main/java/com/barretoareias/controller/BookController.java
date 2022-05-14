package com.barretoareias.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.bean.Author;
import com.barretoareias.model.bean.Book;
import com.barretoareias.model.bean.BookCategory;
import com.barretoareias.model.bean.User;
import com.barretoareias.model.bean.UserType;
import com.barretoareias.model.dao.AuthorDAO;
import com.barretoareias.model.dao.BookDAO;
import com.barretoareias.services.AuthService;

@WebServlet("/book")
public class BookController extends HttpServlet {

    private AuthService authService = new AuthService();
    private AuthorDAO authorDAO = new AuthorDAO();
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!canLoadPage(request, response))
            return;

        getAuthors(request);
        getCategories(request);

        request.setAttribute("page", "book");
        var dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

    private void getCategories(HttpServletRequest request) {
        var categories = new ArrayList<String>();

        for (final var c : BookCategory.values()) {
            if (c != BookCategory.Undefined)
                categories.add(c.toString());
        }

        request.removeAttribute("categories");
        request.setAttribute("categories", categories);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!canLoadPage(request, response))
            return;

        var title = request.getParameter("title").toString();
        var author = new Author(Integer.parseInt(request.getParameter("author")));
        var category = BookCategory.valueOf(request.getParameter("category"));

        var book = new Book(title, category, author);

        var result = bookDAO.addBook(book);

        if (result != null) {
            request.setAttribute("message", String.format("Livro %s cadastrado com sucesso!", title));
            request.setAttribute("page", "main");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        } else {
            request.setAttribute("message", String.format("Erro ao cadastrar livro %s!", title));
            request.setAttribute("page", "book");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        }
    }

    private void getAuthors(HttpServletRequest request) {
        var authors = this.authorDAO.search();

        request.removeAttribute("authors");
        request.setAttribute("authors", authors);
    }

    private boolean canLoadPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!authService.isLoggedIn(request, response))
            return false;

        var user = (User) request.getSession().getAttribute("userLogged");

        if (user.getType() != UserType.Admin) {
            request.setAttribute("page", "main");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
            return false;
        }

        return true;
    }
}
