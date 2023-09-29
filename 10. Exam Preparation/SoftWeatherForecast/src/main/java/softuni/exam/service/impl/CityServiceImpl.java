package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static softuni.exam.constants.FilePaths.IMPORT_CITIES_JSON_PATH;
import static softuni.exam.constants.Messages.*;

@Service
public class CityServiceImpl implements CityService {

    private final ValidationUtils validationUtils;
    private final ModelMapper mapper;
    private final Gson gson;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CityServiceImpl(ValidationUtils validationUtils,
                           ModelMapper mapper,
                           Gson gson,
                           CityRepository cityRepository,
                           CountryRepository countryRepository) {
        this.validationUtils = validationUtils;
        this.mapper = mapper;
        this.gson = gson;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(IMPORT_CITIES_JSON_PATH);
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder sb = new StringBuilder();

        List<CityImportDto> cities =
                Arrays.stream(gson.fromJson(readCitiesFileContent(), CityImportDto[].class)).toList();

        for (CityImportDto cityDto : cities) {
            boolean isValid = this.validationUtils.isValid(cityDto);

            Optional<Country> country = getCountryById(cityDto.getCountry());
            if(country.isEmpty()) {
                isValid = false;
            }

            Optional<City> existingCity = this.cityRepository.findCityByName(cityDto.getCityName());
            if(existingCity.isPresent()) {
                isValid = false;
            }

            if(!isValid) {
                sb.append(INVALID_CITY);
                sb.append(System.lineSeparator());
            } else {
                City cityToSave = this.mapper.map(cityDto, City.class);

                cityToSave.setCountry(country.get());

                this.cityRepository.saveAndFlush(cityToSave);

                sb.append(String.format(VALID_CITY,
                        cityDto.getCityName(),
                        cityDto.getPopulation()));
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }

    private Optional<Country> getCountryById(Integer id) {
        return this.countryRepository.findById(id);
    }

}
