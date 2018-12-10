package test;

import hibernate.Manager;
import hibernate.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

import java.io.IOException;

public class MenagerTest {

    private Book book;
    private Friend friend;
    private Address addres;
    private Author author;
    private Location location;

    @Before
    public void seetUp() {
        book = new Book();
        friend = new Friend();
        addres = new Address();
        author = new Author();
        location = new Location();

    }


    @Test
    public void SerialDeserialTest() {

        int a = 30;
        book.setId(32);
        book.setTitle("Bieguni");
        book.setAuthor(author);
        book.setFriend(friend);
        book.setLocation(location);
        book.setPrice(a);
        Book book2 = new Book();
        Book book3 = new Book();
        try {
            Manager.SerializeObject("test", "json", book);
            book2 = Manager.DeserializeBook("test", "json");
            Manager.SerializeObject("test", "xml", book);
            book3 = Manager.DeserializeBook("test", "xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(book.getId(), book2.getId());
        assertEquals(book.getTitle(), book2.getTitle());

        assertEquals(book.getId(), book3.getId());
        assertEquals(book.getTitle(), book3.getTitle());

    }

    @Test
    public void databaseconnecttest() {

        book.setTitle("Sezon migracji na północ");
        book.setPrice(20);
        Book booktodatabase = new Book();

        try {
            Manager.SerializeObject("Example", "json", book);
            booktodatabase = Manager.DeserializeBook("Example", "json");
        } catch (IOException e) {
            System.out.println("Error");
        }

        EntityManager entityManager;
        EntityManagerFactory entityManagerFactory = null;


        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");


            entityManager = entityManagerFactory.createEntityManager();

            entityManager.getTransaction().begin();

            entityManager.persist(booktodatabase);
            entityManager.flush();
            entityManager.getTransaction().commit();

            entityManager.getTransaction().begin();

            Book bookfromdatabase = entityManager.find(Book.class, 1);

            assertEquals(booktodatabase.getTitle(), bookfromdatabase.getTitle());
            assertEquals(booktodatabase.getPrice(), bookfromdatabase.getPrice());


        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Error:" + ex);
        } finally {
            entityManagerFactory.close();
        }
    }




}
