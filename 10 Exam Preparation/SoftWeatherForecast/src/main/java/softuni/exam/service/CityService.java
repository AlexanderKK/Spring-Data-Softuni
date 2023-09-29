package softuni.exam.service;

import softuni.exam.models.entity.Country;

import java.io.IOException;
import java.util.Optional;

// TODO: Implement all methods
public interface CityService {

    boolean areImported();

    String readCitiesFileContent() throws IOException;
	
	String importCities() throws IOException;

}
