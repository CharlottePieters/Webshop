package domain.model;

import domain.db.*;

import java.util.List;

public class ShopService {
    private PersonDb personDb = new PersonDbSQL();
    private ProductDb productDb = new ProductDbSQL();

    public ShopService() throws ClassNotFoundException {
    }

    public Person getPerson(String personId) {
        return getPersonDb().get(personId);
    }
    public List<Person> getPersons() {
        return getPersonDb().getAll();
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
}
