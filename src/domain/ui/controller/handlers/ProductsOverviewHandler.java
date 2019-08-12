package domain.ui.controller.handlers;

import domain.model.Product;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductsOverviewHandler extends RequestHandler {

    public ProductsOverviewHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.service.getProducts();
        request.setAttribute("products", products);
        return "productoverview.jsp";
    }
}
