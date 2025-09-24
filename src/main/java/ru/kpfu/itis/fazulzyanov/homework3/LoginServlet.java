package ru.kpfu.itis.fazulzyanov.homework3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String submit = req.getParameter("submit");
        if (submit.equals("Sign Up")) {
            resp.sendRedirect("/sign_up");
            // without return, the server crashes
            return;
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        for (Map.Entry<String, String> entry : LoginPasswordBase.getMap().entrySet()) {
            if (login.equals(entry.getKey()) && password.equals(entry.getValue())) {
                // logic to authenticate user

                // session
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", login);
                httpSession.setMaxInactiveInterval(60 * 60);

                // cookie
                Cookie cookie = new Cookie("user", login);
                cookie.setMaxAge(24 * 60 * 60);

                resp.addCookie(cookie);

                resp.sendRedirect("main.jsp");
                // without return, the server crashes
                return;
            }
        }
        resp.sendRedirect("/login");
    }

}
