package domain.db;

import domain.model.Product;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDbSQL implements ProductDb {
    private String ww = JOptionPane.showInputDialog("Wachtwoord school");

    public ProductDbSQL() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }
    }

    @Override
    public Product get(int id) {
        if (id < 0){
            throw new DbException("No valid id given");
        }

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();){

            ResultSet result = statement.executeQuery("SELECT * FROM product WHERE productId = '" + id + "'");
            result.next();
            int productId = Integer.parseInt(result.getString("productId"));
            String name = result.getString("name");
            String description = result.getString("description");
            Double price = Double.parseDouble(result.getString("price"));

            Product product = new Product(productId, name, description, price);
            return product;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            ResultSet result = statement.executeQuery("select * from product");

            while (result.next()) {
                int productId = Integer.parseInt(result.getString("productId"));
                String name = result.getString("name");
                String description = result.getString("description");
                double price = Double.parseDouble(result.getString("price"));

                Product product = new Product(productId, name, description, price);
                products.add(product);
            }
            return products;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Product product) {
        if (product == null){
            throw new DbException("No product given");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement()) {


            int productId = getNumbeOfProducts() + 1;
            product.setProductId(productId);
            System.out.println(productId);
            String name = product.getName();
            String description = product.getDescription();
            double price = product.getPrice();

            ResultSet userPresent = statement.executeQuery("select * from product where productId = '" + productId + "'");
            if (userPresent.next()){
                throw new DbException("Product already exists");
            }
            else {
                statement.executeUpdate("insert into product values ('" + productId + "', '" + name + "', '" + description + "', '" + price + "')");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {
        if (product == null){
            throw new DbException("No product given");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            int productId = product.getProductId();
            String name = product.getName();
            String description = product.getDescription();
            double price = product.getPrice();

            ResultSet productPresent = statement.executeQuery("select * from product where productId = '" + productId + "'");
            if (!productPresent.next()){
                throw new DbException("No product found");
            }
            else {
                statement.executeUpdate("delete from product where productId = ' " + productId + "'");
                statement.executeUpdate("insert into product values ('" + productId + "', '" + name + "', '" + description + "', '" + price + "')");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        if (id < 0){
            throw new DbException("No valid id given");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            ResultSet productPresent = statement.executeQuery("select * from product where productId = '" + id + "'");
            if (!productPresent.next()){
                throw new DbException("No product found");
            }
            else {
                statement.executeUpdate("delete from product where productId = ' " + id + "'");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumbeOfProducts() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("select count(*) as number from product");
            resultSet.next();
            int number = resultSet.getInt("number");
            return number;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
