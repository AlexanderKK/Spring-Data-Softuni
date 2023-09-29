package bg.softuni.springautomappingobjects.entities.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "country")
@XmlAccessorType(XmlAccessType.FIELD)
public class CountryXmlDTO {

    @XmlAttribute
    private String value;

    public CountryXmlDTO() {}

    public CountryXmlDTO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CountryXmlDTO{" +
                "value='" + value + '\'' +
                '}';
    }

}
