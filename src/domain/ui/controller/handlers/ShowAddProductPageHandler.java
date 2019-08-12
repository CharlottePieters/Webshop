package domain.ui.controller.handlers;

import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddProductPageHandler extends RequestHandler {

    public ShowAddProductPageHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "addProduct.jsp";
    }
}
