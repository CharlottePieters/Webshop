package domain.ui.controller.handlers;

import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePersonHandler extends RequestHandler {

    public DeletePersonHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        this.service.deletePerson(userid);
        RequestHandler handler = new UsersOverviewHandler(this.service);
        return handler.handleRequest(request, response);
    }
}
