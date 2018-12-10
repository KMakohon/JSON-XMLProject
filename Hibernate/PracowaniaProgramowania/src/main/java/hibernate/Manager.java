package hibernate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hibernate.model.Book;
import hibernate.model.Author;
import hibernate.model.Friend;
import hibernate.model.Location;
import hibernate.model.Address;
import hibernate.queries.Queries;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

//import org.joda.time.DateTime;
//import com.fasterxml.jackson.databind.Module;

public class Manager {

    static ObjectMapper jsonMapper = new ObjectMapper();
//    jsonMapper.registerModule(new JodaModule());
    static ObjectMapper xmlMapper = new XmlMapper();
//    xmlMapper.registerModule(new JodaModule());

    public static void SerializeObject(String filename, String suffix, Object something) throws IOException {

        if (suffix.equals("json")) {
            jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
            jsonMapper.writeValue(new File(filename + "." + suffix), something);
            return;
        }

        if (suffix.equals("xml")) {
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(new File(filename + "." + suffix), something);
            return;
        }
    }
        public static Book DeserializeBook(String filename, String suffix) throws IOException {

            if (suffix.equals("json")) {
                return jsonMapper.readValue(new File(filename + "." + suffix), Book.class);
            }
            if (suffix.equals("xml")) {
                return xmlMapper.readValue(new File(filename + "." + suffix), Book.class);
            }
            return null;
        }

    public static Address DeserializeAddress(String filename, String suffix) throws IOException {

        if (suffix.equals("json")) {
            return jsonMapper.readValue(new File(filename + "." + suffix), Address.class);
        }
        if (suffix.equals("xml")) {
            return xmlMapper.readValue(new File(filename + "." + suffix), Address.class);
        }
        return null;
    }

    public static Friend DeserializeFriend(String filename, String suffix) throws IOException {

        if (suffix.equals("json")) {
            return jsonMapper.readValue(new File(filename + "." + suffix), Friend.class);
        }
        if (suffix.equals("xml")) {
            return xmlMapper.readValue(new File(filename + "." + suffix), Friend.class);
        }
        return null;
    }

    public static Location DeserializeLocation(String filename, String suffix) throws IOException {

        if (suffix.equals("json")) {
            return jsonMapper.readValue(new File(filename + "." + suffix), Location.class);
        }
        if (suffix.equals("xml")) {
            return xmlMapper.readValue(new File(filename + "." + suffix), Location.class);
        }
        return null;
    }

    public static Author DeserializeAuthor(String filename, String suffix) throws IOException {

        if (suffix.equals("json")) {
            return jsonMapper.readValue(new File(filename + "." + suffix), Author.class);
        }
        if (suffix.equals("xml")) {
            return xmlMapper.readValue(new File(filename + "." + suffix), Author.class);
        }
        return null;
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Start");


        EntityManager entityManager;
        EntityManagerFactory entityManagerFactory = null;


        Author aut = new Author();
        aut.setName("Steve");
//        aut.setBirth("11/11/11");

        Location loc = new Location();
        loc.setBookstand("1");
        loc.setShelf("12");
        loc.setPlace("1");

        SerializeObject("polka", "json", loc);
        SerializeObject("Steve", "json", aut);

        Author steve = new Author();
        new Location();
        Location pol;

        pol = DeserializeLocation("polka", "json");
        System.out.println("Polka " + pol.getShelf());
        steve = DeserializeAuthor("Steve", "json");

//        System.out.println("Author " + steve.getName() + " " + steve.getDate());
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");


            entityManager = entityManagerFactory.createEntityManager();



            Location loc2 = new Location();
            loc2.setBookstand("1");
            loc2.setShelf("1");
            loc2.setPlace("20");


            Location loc3 = new Location();
            loc3.setBookstand("1");
            loc3.setShelf("1");
            loc3.setPlace("20");

            Author aut2 = new Author();
            aut2.setName("Lisa See");
//            aut2.setBirth("test test");

            Book book = new Book();
            book.setTitle("JOBS");
            book.setPrice(20);

            Book book2 = new Book();
            book2.setTitle("Kwiat śniegu i sekretny wachlarz");
            book2.setPrice(35);

            Friend maciek = new Friend();
            maciek.setName("Maciek");
            maciek.setBook(book2);

            Address adres = new Address();
            adres.setCity("Poznan");
            adres.setStreet("Poznanska");
            adres.setNumber(5);
            maciek.setAddress(adres);

            entityManager.getTransaction().begin();

            book.setAuthor(aut);
            book2.setAuthor(aut2);

            aut2.setBook(book2);
            aut2.setBook(book);

            aut.setBook(book);
            entityManager.persist(loc);

            entityManager.persist(loc2);
            entityManager.persist(loc3);
            entityManager.persist((book));


            entityManager.persist((book2));
            entityManager.persist(aut);
            entityManager.persist(aut2);
            entityManager.persist(maciek);

            entityManager.persist(adres);

            entityManager.flush();

            entityManager.getTransaction().commit();



/*
            System.out.println("Location: " + loc.getId() + " regał: " + loc.getBookstand() + " półka: " + loc.getShelf() + " miejsce: " +  loc.getPlace());
            System.out.println("Location: " + loc2.getId() + " regał: " + loc2.getBookstand() + " półka: " + loc2.getShelf() + " miejsce: " +  loc2.getPlace());
            System.out.println("Location: " + loc3.getId() + " regał: " + loc3.getBookstand() + " półka: " + loc3.getShelf() + " miejsce: " +  loc3.getPlace());
            System.out.println("Autor: " +aut.getId() + " Imię: " + aut.getName() + " Książki: "+ aut.getBooksSize());
            System.out.println("Autor: " +aut2.getId() + " Imię: " + aut2.getName() + " Książki: "+ aut2.getBooksSize());
            System.out.println("Książka: " + book.getId() + " tytuł: " + book.getTitle());
            System.out.println("Książka: " + book2.getId() + " tytuł: " + book2.getTitle());
            entityManager.getTransaction().commit();
*/
        //getBbA(entityManager);
            Queries q = new Queries(entityManager);
            List<Book> books = q.getAllBooksofAuthor("'Lisa See'");
            System.err.println("test: " + books.get(0).getTitle());
//        hmbhta(entityManager);
            System.out.println(q.getAllAuthors().get(1).getName());
            System.out.println("Total price: " + q.getTotalPrice());

            System.out.println("Page of books " + q.getAllBooksPage(1).get(0).getTitle());
            System.out.println("All friends: " + q.getAllFriend().get(0).getName());

            System.out.println("Done");




        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Error:" + ex);
        } finally {
            entityManagerFactory.close();
        }

        return;

    }
/*    static void getBbA(EntityManager entityManager) {

        List<String> booksby = new Queries(entityManager).getBookByAuthor("Lisa See");

        System.out.println(booksby.size());
*/
    }
/*
    static void hmbhta(EntityManager entityManager) {

        int HowMany= new Queries(entityManager).HowManyBookHaveAuthor("Lisa See");

        System.out.println("1. "+ HowMany);

    }
*/
