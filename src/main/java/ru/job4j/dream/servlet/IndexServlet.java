package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * IndexServlet.
 * Controller for index.jsp.
 */
public class IndexServlet extends HttpServlet {
    /**
     * Handles get requests.
     * @param req request.
     * @param resp response.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
