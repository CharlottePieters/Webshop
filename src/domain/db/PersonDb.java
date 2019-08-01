package domain.db;

import domain.model.Person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface PersonDb {
    Person get(String personId) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    List<Person> getAll() throws UnsupportedEncodingException, NoSuchAlgorithmException;

    void add(Person person);

    void update(Person person);

    void delete(String personId);

    int getNumberOfPersons();
}
