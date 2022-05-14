package com.barretoareias.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.bean.Book;
import com.barretoareias.model.bean.BookCategory;
import com.barretoareias.model.bean.User;
import com.barretoareias.model.dao.AuthorDAO;
import com.barretoareias.model.dao.BookDAO;
import com.barretoareias.model.dao.BorrowedBookDAO;
import com.barretoareias.services.AuthService;

@WebServlet("/booklist")
public class BookListController extends HttpServlet {

    private final AuthService authService = new AuthService();

    private final BookDAO bookDAO = new BookDAO();
    private final BorrowedBookDAO borrowedBookDAO = new BorrowedBookDAO();
    private final AuthorDAO authorDAO = new AuthorDAO();

    private String title;
    private String author;
    private String available;
    private BookCategory category;

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!authService.isLoggedIn(request, response))
            return;

        filterRequest(request);
        getCategories(request);
        getAuthors(request);
        search(request);

        request.setAttribute("page", "booklist");
        var dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

    private void filterRequest(HttpServletRequest request) {
        this.title = request.getParameter("title") != null ? request.getParameter("title") : "";
        this.author = request.getParameter("author") != null ? request.getParameter("author") : "";
        this.available = request.getParameter("available") != null ? request.getParameter("available") : "";

        this.category = request.getParameter("category") != null && request.getParameter("category") != ""
                ? BookCategory.valueOf(request.getParameter("category"))
                : BookCategory.Undefined;
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

    private void getAuthors(HttpServletRequest request) {
        var authors = this.authorDAO.search();

        request.removeAttribute("authors");
        request.setAttribute("authors", authors);
    }

    private void search(HttpServletRequest request) {
        var books = bookDAO.searchBooks(title, author, available, category);

        request.removeAttribute("booklist");
        request.setAttribute("booklist", books);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!authService.isLoggedIn(request, response))
            return;

        var user = (User) request.getSession().getAttribute("userLogged");
        var book = new Book(Integer.parseInt(request.getParameter("book")));
        request.removeAttribute("message");
        if (!borrowedBookDAO.borrowBook(user, book)) {
            request.setAttribute("message", "Nao foi possivel pegar este livro emprestado");
        } else {
            request.setAttribute("message", "Livro emprestado com sucesso");
        }

        this.processRequest(request, response);
    }
}
