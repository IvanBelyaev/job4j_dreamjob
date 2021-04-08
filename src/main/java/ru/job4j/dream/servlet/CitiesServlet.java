package ru.job4j.dream.servlet;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CitiesServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(CitiesServlet.class.getName());
    /**
     * Handles post requests.
     * Returns all cities.
     * @param req request.
     * @param resp response.
     * @throws IOException possible exception.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<Integer, String> allCities = PsqlStore.instOf().findAllCities();
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        JSONObject answer = new JSONObject();
        for (Map.Entry<Integer, String> city : allCities.entrySet()) {
            answer.put(city.getKey(), city.getValue());
        }
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(answer.toJSONString());
        writer.flush();
    }

    /**
     * Handles get requests.
     * Returns the city by ID.
     * @param req request.
     * @param resp response.
     * @throws IOException possible exception.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int cityId = Integer.parseInt(req.getParameter("cityId"));
        String cityName = PsqlStore.instOf().findCityById(cityId);
        PrintWriter writer = resp.getWriter();
        
        writer.println(cityName);
        writer.flush();
    }
}
