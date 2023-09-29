package bg.softuni.springautomappingobjects.entities.dtos.addresses;

import bg.softuni.springautomappingobjects.entities.dtos.CountryXmlDTO;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "address-XML")
@XmlAccessorType(XmlAccessType.FIELD)
public class AddressXmlDTO {

    @XmlAttribute(name="address-ID")
    private int id;

    @XmlElement(name = "country")
    private String country;

    @XmlElement
    private String city;

    public AddressXmlDTO() {}

    public AddressXmlDTO(int id, String country, String city) {
        this.id = id;
        this.country = country;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Address(id: %d, country: %s, city: %s)", id, country, city);
    }

}
