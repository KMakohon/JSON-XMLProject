package hibernate.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "FRIEND")
public class Friend {

    @Id
    @GeneratedValue(generator = "friend")
    @SequenceGenerator(name = "friend", sequenceName = "friend_seq")
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="address", referencedColumnName = "id")
    Address address;

    @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<Book>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBook(Book book) {
        this.books.add(book);
    }

    public void setAddress(Address adres) {
        this.address = adres;
    }
}
