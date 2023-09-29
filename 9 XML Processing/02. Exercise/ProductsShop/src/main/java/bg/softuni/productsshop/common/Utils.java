package bg.softuni.productsshop.common;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.nio.file.Path;

public enum Utils {

    ;
    public static <T> void writeXmlIntoFile(T data, Path filePath) throws JAXBException {
        File file = filePath.toFile();

        JAXBContext context = JAXBContext.newInstance(data.getClass());
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        marshaller.marshal(data, file);
    }

}
