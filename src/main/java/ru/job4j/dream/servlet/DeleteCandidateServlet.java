package ru.job4j.dream.servlet;

import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteCandidateServlet extends HttpServlet {
    /**
     * Handles get requests.
     * @param req request.
     * @param resp response.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        new File("/home/gh0st/images/" + id).delete();
        PsqlStore.instOf().deleteCandidate(Integer.parseInt(id));
        req.getRequestDispatcher("/candidates.do").forward(req, resp);
    }
}
