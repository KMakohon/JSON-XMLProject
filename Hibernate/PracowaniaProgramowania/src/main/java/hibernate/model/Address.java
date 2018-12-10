package hibernate.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

    @Entity
    @Table(name = "ADDRESS")
    public class Address {

        @Id
        @GeneratedValue(generator = "address")
        @SequenceGenerator(name = "address", sequenceName = "address_seq")
        @Column(name = "id")
        private int id;

        @Column(name = "city", nullable = false)
        private String city;

        @Column(name = "street", nullable = false)
        private String street;

        @Column(name = "number", nullable = false)
        private int number;

/*
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="friend", referencedColumnName = "id")
        Friend friend;
*/

        public int getId() {
            return id;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getStreet() {
            return street;
        }

        public String getNumber() {
            return city;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNmber() {
            return number;
        }

        }
