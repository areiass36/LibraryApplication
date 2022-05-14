package com.barretoareias.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.bean.Author;
import com.barretoareias.model.bean.User;
import com.barretoareias.model.bean.UserType;
import com.barretoareias.model.dao.AuthorDAO;
import com.barretoareias.services.AuthService;

@WebServlet("/author")
public class AuthorController extends HttpServlet {

    private AuthService authService = new AuthService();
    private AuthorDAO authorDAO = new AuthorDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!canLoadPage(request, response))
            return;

        var name = request.getParameter("name");
        var author = new Author(name);

        var result = authorDAO.addAuthor(author);
        if (result != null) {
            request.setAttribute("message", String.format("Autor %s cadastrado com sucesso!", name));
            request.setAttribute("page", "main");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        } else {
            request.setAttribute("message", String.format("Erro ao cadastrar autor %s!", name));
            request.setAttribute("page", "author");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!canLoadPage(request, response))
            return;

        request.setAttribute("page", "author");
        var dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
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
