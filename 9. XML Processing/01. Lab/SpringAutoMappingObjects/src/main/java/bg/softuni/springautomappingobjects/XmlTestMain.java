package bg.softuni.springautomappingobjects;

import bg.softuni.springautomappingobjects.entities.dtos.CountryXmlDTO;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.AddressXmlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

@Component
public class XmlTestMain implements CommandLineRunner {

    private final Scanner scanner;
    private final JAXBContext addressContext;
    private final JAXBContext countryContext;

    @Autowired
    public XmlTestMain(Scanner scanner,
                       @Qualifier("addressContext") JAXBContext addressContext,
                       @Qualifier("countryContext") JAXBContext countryContext) {
        this.scanner = scanner;
        this.addressContext = addressContext;
        this.countryContext = countryContext;
    }

    @Override
    public void run(String... args) throws Exception {
//        CountryXmlDTO countryXmlDTO = new CountryXmlDTO("Bulgaria");

//        AddressXmlDTO addressXmlDTO = new AddressXmlDTO(5, "Bulgaria", "Vidin");

        // Java Object -> XML

//        Marshaller countryMarshaller = countryContext.createMarshaller();
//        countryMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        countryMarshaller.marshal(countryXmlDTO, System.out);
        AddressXmlDTO addressXmlDTO = new AddressXmlDTO(7, "Bulgaria", "Varna");

        // Marshal Object to XML File

        Marshaller addressMarshaller = addressContext.createMarshaller();
        addressMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        String fileLocation = "src\\main\\resources\\files\\output\\address.xml";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileLocation));

        addressMarshaller.marshal(addressXmlDTO, bufferedWriter);
        bufferedWriter.close();

        System.out.println("# Address data written to XML #");
        System.out.println();
        addressMarshaller.marshal(addressXmlDTO, System.out);

        // Unmarshal Object from XML File

        InputStream inputStream = getClass().getResourceAsStream("/files/output/address.xml");
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));

        Unmarshaller unmarshaller = addressContext.createUnmarshaller();
        AddressXmlDTO addressDTOFromXML = (AddressXmlDTO) unmarshaller.unmarshal(bufferedReader);

        System.out.println();
        System.out.println("# Address data read from XML #");
        System.out.println();
        System.out.println(addressDTOFromXML);

        // XML -> Java Object

//        <?xml version="1.0" encoding="UTF-8" standalone="yes"?><address-XML address-ID="5"><countries><country value="Bulgaria"/><country value="Bulgaria"/><country value="Bulgaria"/></countries><city>Vidin</city></address-XML>

//        Unmarshaller unmarshaller = addressContext.createUnmarshaller();
//        AddressXmlDTO addressDTO = (AddressXmlDTO) unmarshaller.unmarshal(System.in);
//        System.out.println(addressDTO);

//        CountryXmlDTO countryDTO = (CountryXmlDTO) unmarshaller.unmarshal(System.in);

//        System.out.println(addressDTO);
//        System.out.println(countryDTO);

    }

}
