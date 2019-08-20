package domain.ui.controller;

import domain.db.DbException;
import domain.model.ShopService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private ShopService service;
    private HandlerFactory handlerFactory = new HandlerFactory();

    @Override
    public void init() throws ServletException {
        super.init();

        ServletContext context = getServletContext();

        Properties properties = new Properties();
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        properties.setProperty("url", context.getInitParameter("url"));

        try {
            service = new ShopService(properties);
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    public Controller() throws Exception {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
        try {
            String action = request.getParameter("action");
            if (action == null){
                action = "";
            }
            RequestHandler handler = this.handlerFactory.getHandler(action, this.service);
            String destination = handler.handleRequest(request, response);
            request.getRequestDispatcher(destination).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
