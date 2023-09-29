package bg.softuni.productsshop.domain.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToOne(optional = false)
    @Fetch(FetchMode.JOIN)
    private User seller;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
    }

    public Product(String name, BigDecimal price, User buyer, User seller) {
        this.setName(name);
        this.setPrice(price);
        this.setBuyer(buyer);
        this.setSeller(seller);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() < 3) {
            throw new IllegalArgumentException("Incorrect name.");
        }

        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", buyer=" + buyer +
                ", seller=" + seller +
                '}';
    }

}
