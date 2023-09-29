package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class XmlParser {

    static {
        System.out.println("Print on class load");
    }

    public <T> T fromFile(File filePath, Class<T> object) throws JAXBException, IOException {
        final JAXBContext context = JAXBContext.newInstance(object);

        final Unmarshaller unmarshaller = context.createUnmarshaller();

        final FileReader fileReader = new FileReader(filePath);

        return (T) unmarshaller.unmarshal(fileReader);
    }

}
