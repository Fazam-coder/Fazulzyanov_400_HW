package ru.kpfu.itis.fazulzyanov.homework4.filter;

import ru.kpfu.itis.fazulzyanov.homework3.LoginPasswordBase;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/sign_up")
public class TooMuchUsersFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (LoginPasswordBase.getMap().size() >= 3) {
            res.sendRedirect("/login");
        } else {
            chain.doFilter(req, res);
        }
    }
}
