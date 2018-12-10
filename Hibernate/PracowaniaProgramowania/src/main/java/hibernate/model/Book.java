package hibernate.model;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "BOOK")

public class Book {

    @Id
    @GeneratedValue(generator = "book")
    @SequenceGenerator(name = "book", sequenceName = "book_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private int  price;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Friend friend;


    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="location", referencedColumnName = "id")
    Location location;

    //public Book() {}


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public Location getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice( int price ) {
        this.price = price;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

   // public void setFriend( Friend friend ) {
   //     this.friend = friend;
   // }


}