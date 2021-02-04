package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CandidateServlet.
 * Handles the addition of a new candidate.
 */
public class CandidateServlet extends HttpServlet {
    /**
     * Handles post requests.
     * @param req request.
     * @param resp response.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Store.instOf().save(new Candidate(0, name));
        resp.sendRedirect(req.getContextPath() + "/candidates.jsp");
    }
}
