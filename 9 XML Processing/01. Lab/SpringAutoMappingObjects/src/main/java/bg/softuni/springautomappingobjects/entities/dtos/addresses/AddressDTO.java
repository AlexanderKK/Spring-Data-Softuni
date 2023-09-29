package bg.softuni.springautomappingobjects.entities.dtos.addresses;

import com.google.gson.annotations.Expose;

public class AddressDTO extends CreateAddressDTO {

    @Expose
    private Long id;

    public AddressDTO() {
        super();
    }

    public AddressDTO(String country, String city) {
        super(country, city);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
