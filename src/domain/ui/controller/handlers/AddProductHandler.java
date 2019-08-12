package domain.ui.controller.handlers;

import domain.model.Product;
import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddProductHandler extends RequestHandler {

    public AddProductHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<String>();
        Product product = new Product();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try {
            product.setName(name);
            request.setAttribute("name", name);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try{
            product.setDescription(description);
            request.setAttribute("description", description);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            product.setPrice(price);
            request.setAttribute("price", price);

        }
        catch (Exception e){
            errors.add(e.getMessage());
        }

        if (errors.isEmpty()) {
            try {
                this.service.addProduct(product);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }


        if (errors.isEmpty()){
            RequestHandler handler = new ProductsOverviewHandler(this.service);
            return handler.handleRequest(request, response);
        }
        else {
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }
}
