package domain.ui.controller.handlers;

import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortUsersHandler extends RequestHandler {

    public SortUsersHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String sortStyleString = request.getParameter("sortStyle");
        Cookie sortStyle = new Cookie("sortStyle", sortStyleString);
        response.addCookie(sortStyle);

        RequestHandler handler = new UsersOverviewHandler(this.service);
        return handler.handleRequest(request, response);
    }
}
