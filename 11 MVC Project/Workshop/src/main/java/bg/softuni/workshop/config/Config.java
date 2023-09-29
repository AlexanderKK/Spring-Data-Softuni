package bg.softuni.workshop.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class Config {

    @Bean
    public ModelMapper createModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(
                new Converter<>() {
                     @Override
                     public LocalDate convert(MappingContext<String, LocalDate> mappingContext) {
                         return LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                     }
                 },
                String.class, LocalDate.class);

        return modelMapper;
    }

    @Bean
    public Validator createValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
