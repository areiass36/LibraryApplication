package com.barretoareias.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.dao.BorrowedBookDAO;
import com.barretoareias.services.AuthService;

@WebServlet("/returnBook")
public class ReturnBookController extends HttpServlet {

    private final AuthService authService = new AuthService();
    private final BorrowedBookDAO borrowedBookDAO = new BorrowedBookDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!this.authService.isLoggedIn(request, response))
            return;

        var bookId = Integer.parseInt(request.getParameter("book"));
        var success = borrowedBookDAO.returnBook(bookId);

        request.removeAttribute("message");

        if (success)
            request.setAttribute("message", "Livro devolvido com sucesso");
        else
            request.setAttribute("message", "Erro ao devolver livro");

        request.setAttribute("page", "main");
        var dispacher = request.getRequestDispatcher("index");
        dispacher.forward(request, response);
    }
}
