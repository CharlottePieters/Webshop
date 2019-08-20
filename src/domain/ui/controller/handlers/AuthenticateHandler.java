package domain.ui.controller.handlers;

import domain.model.Person;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticateHandler extends RequestHandler {

    public AuthenticateHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        Person person = this.service.getUserIfAuthenticated(userId, password);

        if (person == null){
            request.setAttribute("error", "No valid userid/password");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", person);

        RequestHandler handler = new ShowIndexHandler(this.service);
        return handler.handleRequest(request, response);
    }
}
