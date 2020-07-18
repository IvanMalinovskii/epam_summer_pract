package by.gstu.edu.theatre.servlets;

import by.gstu.edu.theatre.entities.categories.Byn;
import by.gstu.edu.theatre.entities.categories.Category;
import by.gstu.edu.theatre.entities.categories.HallRepository;
import by.gstu.edu.theatre.entities.categories.Seat;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jdk.nashorn.internal.parser.Token;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private Gson gson;
    private HallRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        repository = HallRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateId = req.getParameter("id");
        if (dateId == null){ resp.sendError(HttpServletResponse.SC_BAD_REQUEST); return; }

        String dateHall = gson.toJson(repository.getHallByDateId(Long.parseLong(dateId)));
        if (dateHall == null) dateHall = "{}";
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write(dateHall);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject request = new JsonParser().parse(req.getReader()).getAsJsonObject();
        long dateId = request.get("dateId").getAsLong();
        String categoryName = request.get("category").getAsString();
        String price = request.get("price").getAsString();
        List<Seat> seats = gson.fromJson(request.get("seats").getAsJsonArray(), new TypeToken<List<Seat>>(){}.getType());

        repository.changeStates(dateId, new Category(categoryName, seats, new Byn(price)), false);

        // TODO: set in DB

        resp.getWriter().write("{status: 'ok'}");
    }
}
