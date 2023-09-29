package bg.softuni.productsshop.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column
    private int age;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<User> friends;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    @Fetch(FetchMode.JOIN)
    private Set<Product> boughtProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    @Fetch(FetchMode.JOIN)
    private Set<Product> soldProducts;

    public User() {
        this.friends = new HashSet<>();
        this.boughtProducts = new HashSet<>();
        this.soldProducts = new HashSet<>();
    }

    public User(String firstName, String lastName, int age) {
        this();

        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.length() < 3) {
            throw new IllegalArgumentException("Incorrect lastname.");
        }

        this.lastName = lastName;
    }

    public String getFullName() {
        return ((getFirstName() == null ? "" : getFirstName()) +
                " " +
                (getLastName() == null ? "" : getLastName())).trim();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public Set<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<Product> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<Product> soldProducts) {
        this.soldProducts = soldProducts;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", friends=" + friends +
                ", boughtProducts=" + boughtProducts +
                ", soldProducts=" + soldProducts +
                '}';
    }

}
