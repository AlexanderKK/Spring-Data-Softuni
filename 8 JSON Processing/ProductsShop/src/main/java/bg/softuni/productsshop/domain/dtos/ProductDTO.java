package bg.softuni.productsshop.domain.dtos;

import bg.softuni.productsshop.domain.entities.User;

import java.math.BigDecimal;

public class ProductDTO {

    private String name;
    private BigDecimal price;
    private User seller;

    public ProductDTO() {}

    public ProductDTO(String name, BigDecimal price, User seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public ProductInPriceRangeDTO toProductInPriceRange() {
        return new ProductInPriceRangeDTO(name, price, seller.getFullName());
    }

}
