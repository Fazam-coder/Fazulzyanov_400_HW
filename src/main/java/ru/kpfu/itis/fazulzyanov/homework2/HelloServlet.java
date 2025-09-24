package ru.kpfu.itis.fazulzyanov.homework2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "Hello", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        addHeaders(writer, req);
        writer.println("Your parameters:");
        Map<String, String[]> params = req.getParameterMap();
        for (String key : params.keySet()) {
            writer.println(key + ": " + String.join(", ", params.get(key)));
        }
        writer.println("This is from GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        addHeaders(writer, req);
        String body = req.getReader().lines().collect(Collectors.joining());
        writer.println("Your data:");
        writer.println(body);
        writer.println("This is from POST");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        addHeaders(writer, req);
        String body = req.getReader().lines().collect(Collectors.joining());
        writer.println("Your data:");
        writer.println(body);
        writer.println("This is from PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        addHeaders(writer, req);
        String body = req.getReader().lines().collect(Collectors.joining());
        writer.println("Your data:");
        writer.println(body);
        writer.println("This is from DELETE");
    }

    private void addHeaders(PrintWriter writer, HttpServletRequest req) {
        writer.println("Your headers:");
        Iterator<String> iter = req.getHeaderNames().asIterator();
        while (iter.hasNext()) {
            String headerName = iter.next();
            writer.println(headerName + ": " + req.getHeader(headerName));
        }
    }
}
