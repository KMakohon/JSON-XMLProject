package hibernate.model;

import javax.persistence.*;


@Entity
@Table(name = "LOCATION")
public class Location {

    @Id
    @GeneratedValue(generator = "location")
    @SequenceGenerator(name = "location", sequenceName = "location_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "bookstand", nullable = false)
    private String bookstand;

    @Column(name = "shelf",  nullable = false)
    private String shelf;

    @Column(name = "place",  nullable = false)
    private String place;

    public int getId() {
        return id;
    }
    public void setId( int id ) {
        this.id = id;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf( String shelf ) {
        this.shelf = shelf;
    }
    public String getBookstand() {
        return bookstand;
    }

    public void setBookstand( String  bookstand ) {
        this.bookstand = bookstand;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace( String  place ) {
        this.place = place;
    }


}
