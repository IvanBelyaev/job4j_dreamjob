package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DownloadServlet extends HttpServlet {
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
        File downloadFile = new File("/home/gh0st/images/" + name);
        if (downloadFile.exists()) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFile.getName() + "\"");
            try (FileInputStream stream = new FileInputStream(downloadFile)) {
                resp.getOutputStream().write(stream.readAllBytes());
            }
        }
    }
}
