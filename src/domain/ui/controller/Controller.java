package domain.ui.controller;

import domain.db.PersonDbInMemory;
import domain.db.ProductDbInMemory;
import domain.model.Person;
import domain.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    PersonDbInMemory personDB = new PersonDbInMemory();
    ProductDbInMemory productDB = new ProductDbInMemory();

    public Controller() throws Exception {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = "";
        String destination;

        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        switch (action) {
            case "users":
                destination = usersOverview(request, response);
                break;
            case "signUp":
                destination = "signUp.jsp";
                break;
            case "addPerson":
                destination = addPerson(request, response);
                break;
            case "products":
                destination = productsOverview(request, response);
                break;
            case "addProduct":
                destination = "addProduct.jsp";
                break;
            case "addProductToDB":
                destination = addProduct(request, response);
                break;
            case "updateProduct":
                destination = showUpdateProductPage(request, response);
                break;
            case "updateProductToDB":
                destination = updateProduct(request, response);
                break;
            case "deleteProduct":
                destination = showDeleteProductPage(request, response);
                break;
            case "deleteProductFromDB":
                destination = deleteProduct(request, response);
                break;
            case "deletePerson":
                destination = showDeletePersonPage(request, response);
                break;
            case "deletePersonFromDB":
                destination = deletePerson(request, response);
                break;
            default:
                destination = "index.jsp";
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String usersOverview(HttpServletRequest request, HttpServletResponse response){
        List<Person> persons = this.personDB.getAll();
        request.setAttribute("persons", persons);
        return "personoverview.jsp";
    }

    private String addPerson(HttpServletRequest request, HttpServletResponse response){
        List<String> errors = new ArrayList<String>();
        Person person = new Person();

        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");


        try{
            person.setUserid(userid);
            request.setAttribute("userid",userid);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            person.setFirstName(firstName);
            request.setAttribute("firstName",firstName);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try{
            person.setPassword(password);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            person.setEmail(email);
            request.setAttribute("email", email);

        }
        catch (Exception e){
            errors.add(e.getMessage());
        }
        try {
            person.setLastName(request.getParameter("lastName"));
            request.setAttribute("lastName", lastName);
        }
        catch (Exception e){
            errors.add(e.getMessage());
        }

        if (errors.isEmpty()) {
            try {
                this.personDB.add(person);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }


        if (errors.isEmpty()){
            return usersOverview(request, response);
        }
        else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }


    private String productsOverview(HttpServletRequest request, HttpServletResponse response){
        List<Product> products = this.productDB.getAll();
        request.setAttribute("products", products);
        return "productoverview.jsp";
    }

    private String addProduct(HttpServletRequest request, HttpServletResponse response){
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
                this.productDB.add(product);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
        }


        if (errors.isEmpty()){
            return productsOverview(request, response);
        }
        else {
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }

    private String showUpdateProductPage(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = this.productDB.get(productId);
        request.setAttribute("product", product);
        return "updateProduct.jsp";
    }

    private String updateProduct(HttpServletRequest request, HttpServletResponse response) {
        String productIdString = request.getParameter("productId");
        int productId = Integer.parseInt(productIdString);
        Product product = this.productDB.get(productId);

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price = request.getParameter("price");

        try{
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            return productsOverview(request, response);
        }
        catch (Exception e){
            return showUpdateProductPage(request, response);
        }
    }


    private String showDeleteProductPage(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = this.productDB.get(productId);
        request.setAttribute("product", product);
        return "deleteProduct.jsp";
    }

    private String deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int productId = Integer.parseInt(request.getParameter("productId"));
        this.productDB.delete(productId);
        return productsOverview(request, response);
    }

    private String showDeletePersonPage(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        Person person = this.personDB.get(userid);
        request.setAttribute("person", person);
        return "deletePerson.jsp";
    }

    private String deletePerson(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid");
        this.personDB.delete(userid);
        return usersOverview(request, response);
    }


}
