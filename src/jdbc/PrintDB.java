package jdbc;

import domain.model.Person;

import javax.swing.*;
import java.sql.*;

public class PrintDB {
    public static void main(String[] args) throws SQLException {
        String ww = JOptionPane.showInputDialog("Wat is je wachtwoord?");
        Connection connection = DriverManager.getConnection("jdbc:postgresql://databanken.ucll.be:51819/2TXVT?sslmode=require&currentSchema=r0647075", "r0647075", ww);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM person");

        while(result.next()){
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String userid = result.getString("userid");
            String email = result.getString("email");
            String password = result.getString("password");
            try {
                Person person = new Person(userid, email, password, firstname, lastname);
                System.out.println(person.toString());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        statement.close();
        connection.close();
    }
}
