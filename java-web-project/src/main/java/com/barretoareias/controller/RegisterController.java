package com.barretoareias.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.barretoareias.model.bean.User;
import com.barretoareias.model.bean.UserType;
import com.barretoareias.model.dao.UserDAO;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("page", "register");
        var dispacher = request.getRequestDispatcher("index.jsp");
        dispacher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final var name = request.getParameter("name");
        final var email = request.getParameter("email");
        final var password = request.getParameter("password");

        final var user = new User(name, email, password, UserType.Student);

        final var result = userDAO.registerUser(user);

        if (result != null) {
            final var session = request.getSession();
            session.setAttribute("userLogged", result);
            request.setAttribute("message", String.format("Bem-vindo %s", user.getName()));
            request.setAttribute("page", "main");
            var dispacher = request.getRequestDispatcher("index");
            dispacher.forward(request, response);
        } else {
            request.setAttribute("message", "Usuario com essas informacoes ja existe");
            request.setAttribute("page", "register");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
        }
    }

}
