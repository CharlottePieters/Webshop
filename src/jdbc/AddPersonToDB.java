package jdbc;

import domain.model.Person;

import javax.swing.*;
import java.sql.*;

public class AddPersonToDB {
    public static void main(String[] args) throws SQLException {
        String firstname = JOptionPane.showInputDialog("Firstname:");
        String lastname = JOptionPane.showInputDialog("Lastname:");
        String email = JOptionPane.showInputDialog("Email:");
        String userid = JOptionPane.showInputDialog("User ID:");
        String password = JOptionPane.showInputDialog("Password:");

        String ww = JOptionPane.showInputDialog("Wat is je wachtwoord?");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", ww);
        Statement statement = connection.createStatement();

        try{
            Person person = new Person(userid, email, password, firstname, lastname);
            statement.executeUpdate("INSERT INTO person VALUES('" + userid + "', '" + email + "', '" + password + "', '" + firstname + "', '" + lastname + "')");
            ResultSet result = statement.executeQuery("SELECT * FROM person");

            while(result.next()){
                firstname = result.getString("firstname");
                lastname = result.getString("lastname");
                userid = result.getString("userid");
                email = result.getString("email");
                password = result.getString("password");
                try {
                    person = new Person(userid, email, password, firstname, lastname);
                    System.out.println(person.toString());
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        catch (Exception e){
            System.out.println(e.getMessage());
        }
        statement.close();
        connection.close();
    }
}
