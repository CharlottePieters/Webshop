package domain.ui.controller.handlers;

import domain.model.Person;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowDeletePersonPageHandler extends RequestHandler {

    public ShowDeletePersonPageHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        Person person = null;
        try {
            person = this.service.getPerson(userid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("person", person);
        return "deletePerson.jsp";
    }
}
