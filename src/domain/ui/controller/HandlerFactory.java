package domain.ui.controller;

import domain.model.ShopService;
import domain.ui.controller.handlers.*;


public class HandlerFactory {
    public domain.ui.controller.RequestHandler getHandler(String action, ShopService service){
        RequestHandler handler;

        switch (action) {
            case "users":
                handler = new UsersOverviewHandler(service);
                break;
            case "signUp":
                handler = new ShowSignUpPageHandler(service);
                break;
            case "addPerson":
                handler = new AddPersonHandler(service);
                break;
            case "products":
                handler = new ProductsOverviewHandler(service);
                break;
            case "addProduct":
                handler = new ShowAddProductPageHandler(service);
                break;
            case "addProductToDB":
                handler = new AddProductHandler(service);
                break;
            case "updateProduct":
                handler = new ShowUpdateProductPageHandler(service);
                break;
            case "updateProductToDB":
                handler = new UpdateProductHandler(service);
                break;
            case "deleteProduct":
                handler = new ShowDeleteProductPageHandler(service);
                break;
            case "deleteProductFromDB":
                handler = new DeleteProductHandler(service);
                break;
            case "deletePerson":
                handler = new ShowDeletePersonPageHandler(service);
                break;
            case "deletePersonFromDB":
                handler = new DeletePersonHandler(service);
                break;
            case "checkPasswordPage":
                handler = new ShowPasswordPageHandler(service);
                break;
            case "checkPassword":
                handler = new CheckPasswordHandler(service);
                break;
            case "quote":
                handler = new QuoteHandler(service);
                break;
            case "sortUsers":
                handler = new SortUsersHandler(service);
                break;
            case "cart":
                handler = new ShowCartHandler(service);
                break;
            case "addToCart":
                handler = new AddToCartHandler(service);
                break;
            case "authenticate":
                handler = new AuthenticateHandler(service);
                break;
            case "logout":
                handler = new LogOutHandler(service);
                break;
            default:
                handler = new ShowIndexHandler(service);
        }
        return handler;
    }



}
