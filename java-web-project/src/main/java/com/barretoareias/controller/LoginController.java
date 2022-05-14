package com.barretoareias.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.dao.UserDAO;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("page", "login");
        var dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final var email = request.getParameter("email");
        final var password = request.getParameter("password");

        final var user = userDAO.loginUser(email, password);
        request.removeAttribute("message");

        if (user != null) {
            final var session = request.getSession();
            session.setAttribute("userLogged", user);
            request.setAttribute("message", String.format("Bem-vindo %s", user.getName()));
            request.setAttribute("page", "main");
            var dispacher = request.getRequestDispatcher("index");
            dispacher.forward(request, response);
        } else {
            request.setAttribute("message", "Usuario ou senha errados!");
            request.setAttribute("page", "login");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        }
    }

}
