package domain.model;

import domain.db.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

public class ShopService {
    private PersonDb personDb;
    private ProductDb productDb;

    public ShopService(Properties properties) throws ClassNotFoundException {
        this.personDb = new PersonDbSQL(properties);
        this.productDb = new ProductDbSQL(properties);
    }

    public Person getPerson(String personId) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return getPersonDb().get(personId);
    }
    public List<Person> getPersons() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return getPersonDb().getAll();
    }
    public List<Person> getPersons(String sortingStyle){
        return getPersonDb().getAll(sortingStyle);
    }
    public void addPerson(Person person) {
        getPersonDb().add(person);
    }
    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }
    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }
    private PersonDb getPersonDb() {
        return personDb;
    }

    public Product getProduct(int productId) { return  getProductDb().get(productId); }
    public List<Product> getProducts() { return  getProductDb().getAll(); }
    public void addProduct(Product product) { getProductDb().add(product); }
    public void updateProducts(Product product) { getProductDb().update(product); }
    public void deleteProduct (int id) { getProductDb().delete(id); }
    private ProductDb getProductDb() { return  productDb; }

    public Person getUserIfAuthenticated(String userId, String password){ //returns person if authenticated, null if not
        try {
            Person person = getPerson(userId);
            if (person.isCorrectHashedPassword(password)) { //password correct
                return person;
            }
            else { //password incorrect
                System.out.println("password incorrect");
                return null;
            }
        } catch (Exception e) { //userId is incorrect or other error
            e.printStackTrace();
            return null;
        }
    }
}
