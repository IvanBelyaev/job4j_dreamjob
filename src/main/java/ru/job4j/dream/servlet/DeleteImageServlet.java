package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteImageServlet extends HttpServlet {
    /**
     * Handles get requests.
     * @param req request.
     * @param resp response.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("id");
        Files.delete(Path.of("/home/gh0st/images/" + name));
        req.getRequestDispatcher("/candidates.do").forward(req, resp);
    }
}
