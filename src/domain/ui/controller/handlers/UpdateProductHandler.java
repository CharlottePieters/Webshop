package domain.ui.controller.handlers;

import domain.model.Product;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProductHandler extends RequestHandler {

    public UpdateProductHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String productIdString = request.getParameter("productId");
        int productId = Integer.parseInt(productIdString);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try{
            Double priceDouble = Double.parseDouble(price);
            Product product = new Product(productId, name, description, priceDouble);
            service.updateProducts(product);
            RequestHandler handler = new ProductsOverviewHandler(this.service);
            return handler.handleRequest(request, response);
        }
        catch (Exception e){
            RequestHandler handler = new ShowUpdateProductPageHandler(this.service);
            return handler.handleRequest(request, response);
        }
    }
}
