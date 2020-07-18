package by.gstu.edu.theatre.servlets;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.DateDao;
import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.PlayDao;
import by.gstu.edu.theatre.services.PlayService;
import by.gstu.edu.theatre.services.parsers.json.PlayDatesJsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/plays")
public class PlaysServlet extends HttpServlet {
    private PlayService playService;
    private JsonParser parser;

    @Override
    public void init() throws ServletException {
        super.init();
        playService = new PlayService(new PlayDao(), new DateDao(), new PlayDatesJsonParser());
        parser = new JsonParser();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String responseString;
        String parameter =req.getParameter("id");
        if (parameter == null) {
            responseString = playService.getAllPlays();
        }
        else {
            responseString = playService.getPlay(Long.parseLong(parameter));
        }
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        writer.write(responseString);
        writer.close();
    }
}
