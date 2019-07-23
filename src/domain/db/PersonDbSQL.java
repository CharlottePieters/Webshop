package domain.db;

import domain.model.Person;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDbSQL implements PersonDb {
    private String ww = JOptionPane.showInputDialog("Wachtwoord school:");

    public PersonDbSQL() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }
    }

    @Override
    public Person get(String personId) {
        if (personId == null){
            throw new DbException("No id given");
        }

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();){

            ResultSet result = statement.executeQuery("SELECT * FROM person WHERE userid = '" + personId + "'");
            result.next();
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");

            Person person = new Person(userid, email, password, firstname, lastname);
            return person;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            ResultSet result = statement.executeQuery("select * from person");

            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String password = result.getString("password");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");

                Person person = new Person(userid, email, password, firstname, lastname);
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
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            String userid = person.getUserid();
            String email = person.getEmail();
            String password = person.getPassword();
            String firstname = person.getFirstName();
            String lastname = person.getLastName();

            ResultSet userPresent = statement.executeQuery("select * from person where userid = '" + userid + "'");
            if (userPresent.next()){
                throw new DbException("User already exists");
            }
            else {
                statement.executeUpdate("insert into person values ('" + userid + "', '" + email + "', '" + password + "', '" + firstname + "', '" + lastname + "')");
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
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            String userid = person.getUserid();
            String email = person.getEmail();
            String password = person.getPassword();
            String firstname = person.getFirstName();
            String lastname = person.getLastName();

            ResultSet userPresent = statement.executeQuery("select * from person where userid = '" + userid + "'");
            if (!userPresent.next()){
                throw new DbException("No person found");
            }
            else {
                statement.executeUpdate("delete from person where userid = ' " + userid + "'");
                statement.executeUpdate("insert into person values ('" + userid + "', '" + email + "', '" + password + "', '" + firstname + "', '" + lastname + "')");
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
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            ResultSet userPresent = statement.executeQuery("select * from person where userid = '" + personId + "'");
            if (!userPresent.next()){
                throw new DbException("No person found");
            }
            else {
                statement.executeUpdate("delete from person where userid = '" + personId + "'");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", this.ww);
             Statement statement = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery("select count * as number from person");
            int number = resultSet.getInt("number");
            return number;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
