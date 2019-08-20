package domain.ui.controller.handlers;

import domain.model.Product;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class AddToCartHandler extends RequestHandler {

    public AddToCartHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Product product = this.service.getProduct(Integer.parseInt(request.getParameter("productId")));
        int amount = Integer.parseInt(request.getParameter("amount"));

        HttpSession session = request.getSession();
        HashMap<Product, Integer> productWithAmount;

        if (session.isNew() || session.getAttribute("cart") == null){ //creates new HashMap if session is new
            productWithAmount = new HashMap<>();
            session.setMaxInactiveInterval(3600);
        }
        else { //equals to existing HashMap if session is not new
            productWithAmount = (HashMap<Product, Integer>) session.getAttribute("cart");
        }

        productWithAmount.put(product, amount);
        session.setAttribute("cart", productWithAmount); //sets the new HashMap as the cart

        RequestHandler handler = new ShowCartHandler(this.service);
        return handler.handleRequest(request, response);
    }
}
