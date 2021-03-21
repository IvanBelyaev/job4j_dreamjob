package ru.job4j.dream.servlet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GreetingServlet extends HttpServlet {
    private Logger logger = LoggerFactory.getLogger(GreetingServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.setContentType("text/json");
            resp.setCharacterEncoding("UTF-8");
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(req.getReader());
            String name = (String) jsonObject.get("name");
            JSONObject answer = new JSONObject();
            answer.put("message", "Nice to meet you, ");
            answer.put("name", name);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.println(answer.toJSONString());
            writer.flush();
        } catch (ParseException e) {
            logger.warn("Wrong JSON", e);
        }
    }
}
