package domain.ui.controller.handlers;

import domain.model.Person;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
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

        String sortStyle = "";
        for (Cookie cookie : request.getCookies()){
            if (cookie.getName().equals("sortStyle")){
                sortStyle = cookie.getValue();
            }
        }

        try {
            persons = this.service.getPersons(sortStyle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }
}
