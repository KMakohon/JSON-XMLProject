package hibernate.model;

import javax.persistence.*;
import java.util.*;

import org.joda.time.DateTime;


@Entity
@Table(name = "AUTHOR")

public class Author {

    @Id
    @GeneratedValue(generator = "author")
    @SequenceGenerator(name = "author", sequenceName = "author_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name",  nullable = false)
    private String name;

    //@Column(name = "date_of_birth",  nullable = false)
    //private DateTime date;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<Book>();

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setBook(Book book) {
        this.books.add(book);
    }


    public Set<Book> getBooks() { return this.books;}

    //public void setBirth(DateTime date) {
      //  this.date =  date;
    //}
    //public DateTime getDate(){return this.date;}
}
