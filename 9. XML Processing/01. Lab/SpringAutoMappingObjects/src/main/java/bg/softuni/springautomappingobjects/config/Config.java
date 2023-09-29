package bg.softuni.springautomappingobjects.config;

import bg.softuni.springautomappingobjects.entities.dtos.CountryXmlDTO;
import bg.softuni.springautomappingobjects.entities.dtos.addresses.AddressXmlDTO;
import bg.softuni.springautomappingobjects.repositories.AddressRepository;
import bg.softuni.springautomappingobjects.services.AddressService;
import bg.softuni.springautomappingobjects.services.AddressServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.Model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.time.LocalDate;
import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> localDateConverter = context -> LocalDate.parse(context.getSource());
        modelMapper.addConverter(localDateConverter, String.class, LocalDate.class);

        return modelMapper;
    }

    @Bean
    public Scanner createScanner() {
        return new Scanner(System.in);
    }

    @Bean
    public AddressService createAddressService(AddressRepository repository, ModelMapper mapper) {
        return new AddressServiceImpl(repository, mapper);
    }

    @Bean
    public Gson createGson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }

    @Bean("addressContext")
    public JAXBContext createAddressContext() throws JAXBException {
        return JAXBContext.newInstance(AddressXmlDTO.class);
    }

    @Bean("countryContext")
    public JAXBContext createCountryContext() throws JAXBException {
        return JAXBContext.newInstance(CountryXmlDTO.class);
    }

}
