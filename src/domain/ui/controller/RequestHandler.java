package domain.ui.controller;

import domain.model.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class RequestHandler {
    protected ShopService service;

    public RequestHandler(ShopService service){
        this.setService(service);
    }

    public void setService(ShopService service){
        this.service = service;
    }

    public ShopService getService(){
        return this.service;
    }

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

}
