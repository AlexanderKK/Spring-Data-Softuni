package bg.softuni.springautomappingobjects.config;

import bg.softuni.springautomappingobjects.repositories.AddressRepository;
import bg.softuni.springautomappingobjects.services.AddressService;
import bg.softuni.springautomappingobjects.services.AddressServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        return new ModelMapper();
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

}
