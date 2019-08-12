package domain.ui.controller.handlers;

import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductHandler extends RequestHandler {

    public DeleteProductHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        this.service.deleteProduct(productId);
        RequestHandler handler = new ProductsOverviewHandler(this.service);
        return handler.handleRequest(request, response);
    }
}
