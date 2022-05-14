package com.barretoareias.services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthService {

    public boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        var session = request.getSession();
        if (session.getAttribute("userLogged") == null) {
            request.setAttribute("page", "login");
            var dispacher = request.getRequestDispatcher("index.jsp");
            dispacher.forward(request, response);
            return false;
        }
        return true;
    }
}
