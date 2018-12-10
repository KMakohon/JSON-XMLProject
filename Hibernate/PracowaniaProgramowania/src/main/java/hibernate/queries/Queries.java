package hibernate.queries;

import hibernate.model.Author;
import hibernate.model.Book;
import hibernate.model.Friend;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class Queries {

    EntityManager entityManager;

    public Queries(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Book> getAllBooksofAuthor(String name) {
        String pomzapytanie = "SELECT a.id from Author a where name =" + name;
        TypedQuery<Integer> pom = entityManager.createQuery(pomzapytanie, Integer.class);

        String zapytanie = "SELECT c FROM Book c where c.author = " + pom.getSingleResult();
        TypedQuery<Book> query = entityManager.createQuery(
                zapytanie, Book.class);

        return query.getResultList();
    }

    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery(
                "SELECT c FROM Author c", Author.class);

        return query.getResultList();
    }

    public List<Friend> getAllFriend(){
        TypedQuery<Friend> query = entityManager.createQuery(
                "SELECT f FROM Friend f", Friend.class);
        return query.getResultList();

    }

    public long getTotalPrice(){
        Query query = entityManager.createQuery(
        "Select sum(k.price) from Book k", Long.class);
        long k = (long) query.getSingleResult();
        return k;
    }



    public List<Book> getAllBooksPage(int pagenr) {
        //calculate total number
        Query queryTotal = entityManager.createQuery
                ("Select count(e) from Book e");
        long countResult = (long)queryTotal.getSingleResult();

        //create query
        Query query = entityManager.createQuery("Select e FROM Book e");
        //set pageSize
        int pageSize = 5;
        //calculate number of pages
        int pageNumber = (int) ((countResult / pageSize) + 1);

        if (pagenr > pageNumber) pagenr = pageNumber;
        query.setFirstResult((pagenr-1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
    
}