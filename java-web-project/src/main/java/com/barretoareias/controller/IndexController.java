package com.barretoareias.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.bean.User;
import com.barretoareias.model.dao.BorrowedBookDAO;
import com.barretoareias.services.AuthService;

@WebServlet("/index")
public class IndexController extends HttpServlet {

    private final AuthService authService = new AuthService();
    private final BorrowedBookDAO borrowedBookDAO = new BorrowedBookDAO();

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!authService.isLoggedIn(request, response))
            return;
        var user = (User) request.getSession().getAttribute("userLogged");

        var books = borrowedBookDAO.search(user);

        request.setAttribute("borrowedBooks", books);

        request.setAttribute("page", "main");
        var dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }
}
