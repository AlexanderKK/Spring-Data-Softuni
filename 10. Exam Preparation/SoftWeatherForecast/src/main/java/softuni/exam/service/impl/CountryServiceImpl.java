package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static softuni.exam.constants.FilePaths.IMPORT_COUNTRIES_JSON_PATH;
import static softuni.exam.constants.Messages.INVALID_COUNTRY;
import static softuni.exam.constants.Messages.VALID_COUNTRY;

@Service
public class CountryServiceImpl implements CountryService {

    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtils validationUtils;
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository,
                              Gson gson,
                              ModelMapper mapper,
                              ValidationUtils validationUtils) {
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtils = validationUtils;
        this.countryRepository = countryRepository;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(IMPORT_COUNTRIES_JSON_PATH);
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder sb = new StringBuilder();

        List<Country> countries =
                Arrays.stream(gson.fromJson(readCountriesFromFile(), CountryImportDto[].class))
                .filter(countryImportDto -> {
                    boolean isValid = this.validationUtils.isValid(countryImportDto);

                    if (this.countryRepository.findFirstByName(countryImportDto.getCountryName()).isPresent()) {
                        isValid = false;
                    }

                    if (isValid) {
                        this.countryRepository.saveAndFlush(this.mapper.map(countryImportDto, Country.class));

                        sb.append(String.format(VALID_COUNTRY,
                                countryImportDto.getCountryName(),
                                countryImportDto.getCurrency()));
                        sb.append(System.lineSeparator());
                    } else {
                        sb.append(INVALID_COUNTRY);
                        sb.append(System.lineSeparator());
                    }

                    return isValid;
                })
                .map(countryImportDto -> mapper.map(countryImportDto, Country.class))
                .toList();

        return sb.toString().trim();
    }

}
