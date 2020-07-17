package by.gstu.edu.theatre.servlets;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.UserDao;
import by.gstu.edu.theatre.entities.User;
import by.gstu.edu.theatre.services.UserService;
import by.gstu.edu.theatre.services.parsers.json.UserJsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/auth")
public class AuthorizationServlet extends HttpServlet {
    private UserService userService;
    private JsonParser parser;
    private UserJsonParser userJsonParser;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        parser = new JsonParser();
        userJsonParser = new UserJsonParser();
        userService = new UserService(new UserDao(), userJsonParser);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        JsonObject jsonResponse = new JsonObject();
        if (session != null) {
            jsonResponse.addProperty("status", "exist");
            jsonResponse.add("user", parser.parse((String) session.getAttribute("user")));
        }
        else {
            jsonResponse.addProperty("status", "empty");
        }
        resp.getWriter().write(jsonResponse.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject requestJson = getFromRequest(req);
        JsonObject responseJson = new JsonObject();
        String type = requestJson.get("type").getAsString();
        Optional<String> user = type.equals("sign in")? userService.doSignIn(requestJson.toString()) : userService.doSignUp(requestJson.toString());

        if (user.isPresent()) {
            responseJson.addProperty("status", "ok");
            responseJson.add("user", parser.parse(user.get()));
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user.get());
        }
        else {
            responseJson.addProperty("status", "error");
        }

        resp.getWriter().write(responseJson.toString());
    }

    private JsonObject getFromRequest(HttpServletRequest request) throws IOException {
        return parser.parse(request.getReader()).getAsJsonObject();
    }
}
