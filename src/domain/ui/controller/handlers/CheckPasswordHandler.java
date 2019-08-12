package domain.ui.controller.handlers;

import domain.model.Person;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class CheckPasswordHandler extends RequestHandler {

    public CheckPasswordHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        Person person = null;

        String givenPassword = request.getParameter("password");

        try {
            person = this.service.getPerson(userid);
            givenPassword = person.hashPassword(givenPassword);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String password = person.getPassword();
        boolean correct = password.equals(givenPassword);

        request.setAttribute("correct", correct);
        return "checkPassword.jsp";
    }

}
