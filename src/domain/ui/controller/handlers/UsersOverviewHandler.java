package domain.ui.controller.handlers;

import domain.model.Person;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersOverviewHandler extends RequestHandler {
    public UsersOverviewHandler(ShopService service){
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons = null;
        try {
            persons = this.service.getPersons();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }
}
