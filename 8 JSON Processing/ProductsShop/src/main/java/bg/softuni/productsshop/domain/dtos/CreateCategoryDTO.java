package bg.softuni.productsshop.domain.dtos;

import com.google.gson.annotations.Expose;

public class CreateCategoryDTO {

    @Expose
    private String name;

    public CreateCategoryDTO() {}

    public CreateCategoryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateCategoryDTO{" +
                "name='" + name + '\'' +
                '}';
    }

}
