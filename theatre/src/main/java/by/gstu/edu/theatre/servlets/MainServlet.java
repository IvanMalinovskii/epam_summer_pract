package by.gstu.edu.theatre.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sth")
public class MainServlet extends HttpServlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/front/pages/index.html").forward(req, resp);
        //config.getServletContext().getRequestDispatcher("/front/pages/index.html").forward(req, resp);
    }
}
