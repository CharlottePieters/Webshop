package domain.ui.controller.handlers;

import domain.model.Product;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowDeleteProductPageHandler extends RequestHandler {

    public ShowDeleteProductPageHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = this.service.getProduct(productId);
        request.setAttribute("product", product);
        return "deleteProduct.jsp";
    }
}
