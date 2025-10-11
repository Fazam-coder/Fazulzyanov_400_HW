package ru.kpfu.itis.fazulzyanov.homework6;

import ru.kpfu.itis.fazulzyanov.homework5.services.UserService;
import ru.kpfu.itis.fazulzyanov.homework5.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/check-login")
public class CheckLoginServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        resp.setContentType("text/plain");
        resp.getWriter().write(String.valueOf(userService.existsLogin(login)));
    }
}
