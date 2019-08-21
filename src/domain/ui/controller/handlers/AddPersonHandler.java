package domain.ui.controller.handlers;

import domain.model.Person;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddPersonHandler extends RequestHandler {

    public AddPersonHandler(ShopService service){
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<String>();
        Person person = new Person();

        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String role = "customer";


        try{
            person.setUserid(userid);
            request.setAttribute("userid",userid);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstName",firstName);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try{
            person.setPassword(password);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            person.setEmail(email);
            request.setAttribute("email", email);

        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            person.setLastName(lastName);
            request.setAttribute("lastName", lastName);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try{
            person.setRole(role);
        }
        catch (Exception e){

        }

        if (errors.isEmpty()) {
            try {
                this.service.addPerson(person);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }


        if (errors.isEmpty()){
            RequestHandler handler = new UsersOverviewHandler(this.service);
            return handler.handleRequest(request, response);
        }
        else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }
}
