package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * PostServlet.
 * Handles the addition of a new job.
 */
public class PostServlet extends HttpServlet {
    /**
     * Handles get requests.
     * @param req request.
     * @param resp response.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", Store.instOf().findAllPosts());
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }

    /**
     * Handles post request.
     * @param req request.
     * @param resp response.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().save(
                new Post(
                        Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("desc")
                )
        );
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}