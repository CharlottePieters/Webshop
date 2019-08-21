package domain.db;

import domain.model.Person;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDbSQL implements PersonDb {
    private Properties properties;
    private String url;
    private String ww = JOptionPane.showInputDialog("Wachtwoord school:");

    public PersonDbSQL(Properties properties) throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.properties.setProperty("password", this.ww);
            this.url = properties.getProperty("url");
        } catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Person get(String personId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (personId == null){
            throw new DbException("No id given");
        }

        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE userid = ?")){

            statement.setString(1, personId);
            ResultSet result = statement.executeQuery();
            result.next();
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String role = result.getString("role");

            Person person = new Person(userid, email, password, firstname, lastname, role);
            return person;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Person> getAll() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement();) {

            ResultSet result = statement.executeQuery("select * from person");

            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String role = result.getString("role");

                Person person = new Person(userid, email, password, firstname, lastname, role);
                persons.add(person);
            }
            return persons;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Person person) {
        if (person == null){
            throw new DbException("No person given");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement("select * from person where userid = ?");
             PreparedStatement statement2 = connection.prepareStatement("insert into person values (?, ?, ?, ?, ?, ?)")
        ) {

            String userid = person.getUserid();
            String email = person.getEmail();
            String password = person.getPassword();
            String firstname = person.getFirstName();
            String lastname = person.getLastName();
            String role = person.getRole();

            statement.setString(1, userid);
            ResultSet userPresent = statement.executeQuery();
            if (userPresent.next()){
                throw new DbException("User already exists");
            }
            else {
                statement2.setString(1, userid);
                statement2.setString(2, email);
                statement2.setString(3, password);
                statement2.setString(4, firstname);
                statement2.setString(5, lastname);
                statement2.setString(6, role);
                statement2.execute();
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Person person) {
        if (person == null){
            throw new DbException("No person given");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement("select * from person where userid = ?");
             PreparedStatement deleteStatement = connection.prepareStatement("delete from person where userid = ?");
             PreparedStatement statement2 = connection.prepareStatement("insert into person values (?, ?, ?, ?, ?, ?)")
        ) {

            String userid = person.getUserid();
            String email = person.getEmail();
            String password = person.getPassword();
            String firstname = person.getFirstName();
            String lastname = person.getLastName();
            String role = person.getRole();

            statement.setString(1, userid);
            ResultSet userPresent = statement.executeQuery();
            if (!userPresent.next()){
                throw new DbException("No person found");
            }
            else {
                deleteStatement.setString(1, userid);
                deleteStatement.execute();
                statement2.setString(1, userid);
                statement2.setString(2, email);
                statement2.setString(3, password);
                statement2.setString(4, firstname);
                statement2.setString(5, lastname);
                statement2.setString(6, role);
                statement2.execute();
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String personId) {
        if (personId == null){
            throw new DbException("No id given");
        }
        try (Connection connection = DriverManager.getConnection(url, properties);
             PreparedStatement statement = connection.prepareStatement("select * from person where userid = ?");
             PreparedStatement deleteStatement = connection.prepareStatement("delete from person where userid = ?")
        ) {

            statement.setString(1, personId);
            ResultSet userPresent = statement.executeQuery();

            if (!userPresent.next()){
                throw new DbException("No person found");
            }
            else {
                deleteStatement.setString(1, personId);
                deleteStatement.execute();
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("select count * as number from person");
            int number = resultSet.getInt("number");
            return number;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Person> getAll(String sortStyle){
        List<Person> persons = new ArrayList<>();
        ResultSet result;

        try (Connection connection = DriverManager.getConnection(url, properties);
             Statement statement = connection.createStatement();) {

            if (sortStyle.equals("email")){
                result = statement.executeQuery("select * from person order by email");
            }
            else if (sortStyle.equals("firstName")) {
                result = statement.executeQuery("select * from person order by firstname");
            }
            else if (sortStyle.equals("lastName")) {
                result = statement.executeQuery("select * from person order by lastname");
            }
            else {
                result = statement.executeQuery("select * from person");
            }

            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String role = result.getString("role");

                Person person = new Person(userid, email, password, firstname, lastname, role);
                persons.add(person);
            }

            return persons;
        }

        catch (Exception e) {
            throw new DbException(e.getMessage(), e);
        }
    }

}
