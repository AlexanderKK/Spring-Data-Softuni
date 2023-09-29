package bg.softuni.productsshop.domain.dtos.products;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CreateProductDTO {

    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public CreateProductDTO() {}

    public CreateProductDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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

}
