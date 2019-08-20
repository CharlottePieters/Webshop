package domain.ui.controller.handlers;

import domain.model.ShopService;
import domain.ui.controller.RequestHandler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuoteHandler extends RequestHandler {

    public QuoteHandler(ShopService service) {
        super(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String quoteString = request.getParameter("showQuote");
        System.out.println("quote in quotehandler: " + quoteString);
        Cookie quote = new Cookie("quote", quoteString);
        quote.setMaxAge(-1);
        response.addCookie(quote);

        RequestHandler handler = new ShowIndexHandler(this.service);
        return handler.handleRequest(request, response);
    }
}
