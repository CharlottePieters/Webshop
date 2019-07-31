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
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE productId = ?")){

            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
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
             PreparedStatement statement1 = connection.prepareStatement("select * from product where productId = ?");
             PreparedStatement userStatement = connection.prepareStatement("insert into product values (?, ?, ?, ?)")) {

            int productId;
            if (product.getProductId() == 0){
                productId = getNumbeOfProducts() + 1;
                product.setProductId(productId);
            }
            else {
                productId = product.getProductId();
            }
            String name = product.getName();
            String description = product.getDescription();
            double price = product.getPrice();

            statement1.setInt(1, productId);
            ResultSet userPresent = statement1.executeQuery();
            if (userPresent.next()){
                throw new DbException("Product already exists");
            }
            else {
                userStatement.setInt(1, productId);
                userStatement.setString(2, name);
                userStatement.setString(3, description);
                userStatement.setDouble(4, price);
                userStatement.execute();
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
             PreparedStatement statement = connection.prepareStatement("select * from product where productId = ?");
             PreparedStatement deleteStatement = connection.prepareStatement("delete from product where productId = ?");
             PreparedStatement insertStatement = connection.prepareStatement("insert into product values (?, ?, ?, ?)")) {

            int productId = product.getProductId();
            String name = product.getName();
            String description = product.getDescription();
            double price = product.getPrice();

            statement.setInt(1, productId);
            ResultSet productPresent = statement.executeQuery();
            if (!productPresent.next()){
                throw new DbException("No product found");
            }
            else {
                deleteStatement.setInt(1, productId);
                deleteStatement.execute();
                insertStatement.setInt(1, productId);
                insertStatement.setString(2, name);
                insertStatement.setString(3, description);
                insertStatement.setDouble(4, price);
                insertStatement.execute();
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
             PreparedStatement getStatement = connection.prepareStatement("select * from product where productId = ?");
             PreparedStatement deleteStatement = connection.prepareStatement("delete from product where productId = ?")) {

            getStatement.setInt(1, id);
            ResultSet productPresent = getStatement.executeQuery();
            if (!productPresent.next()){
                throw new DbException("No product found");
            }
            else {
                deleteStatement.setInt(1, id);
                deleteStatement.executeUpdate();
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
