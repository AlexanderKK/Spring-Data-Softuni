package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastImportDto;
import softuni.exam.models.dto.wrappers.ForecastsWrapperDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.FilePaths.IMPORT_FORECASTS_XML_PATH;
import static softuni.exam.constants.Messages.INVALID_FORECAST;
import static softuni.exam.constants.Messages.VALID_FORECAST;

@Service
public class ForecastServiceImpl implements ForecastService {

    public static final String PRINT_FORECAST_FORMAT =
            "City: %s:%n" +
            "-min temperature: %.2f%n" +
            "--max temperature: %.2f%n" +
            "---sunrise: %s%n" +
            "----sunset: %s";
    private final XmlParser xmlParser;
    private final ValidationUtils validationUtils;
    private final ModelMapper mapper;
    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;

    @Autowired
    public ForecastServiceImpl(XmlParser xmlParser,
                               ValidationUtils validationUtils,
                               ModelMapper mapper,
                               ForecastRepository forecastRepository, CityRepository cityRepository) {
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
        this.mapper = mapper;
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(IMPORT_FORECASTS_XML_PATH);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        ForecastsWrapperDto forecastsWrapperDto = xmlParser.fromFile(IMPORT_FORECASTS_XML_PATH.toFile(), ForecastsWrapperDto.class);

        StringBuilder sb = new StringBuilder();
        for (ForecastImportDto forecastDto : forecastsWrapperDto.getForecasts()) {
            boolean isValid = validationUtils.isValid(forecastDto);

            if(isValid) {
                Optional<City> referredCity = this.cityRepository.findById(forecastDto.getCity());

                if(referredCity.isEmpty()) {
                    appendErrorToStringBuilder(sb);
                    continue;
                }

                Optional<Forecast> forecast = this.forecastRepository.findFirstByDayOfWeekAndCity(
                        forecastDto.getDayOfWeek(), referredCity.get());

                if(forecast.isPresent()) {
                    appendErrorToStringBuilder(sb);
                    continue;
                }

                Forecast forecastToSave = this.mapper.map(forecastDto, Forecast.class);

                forecastToSave.setCity(referredCity.get());

                forecastToSave.setSunrise(LocalTime.parse(
                        forecastDto.getSunrise(),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));

                forecastToSave.setSunset(LocalTime.parse(
                        forecastDto.getSunset(),
                        DateTimeFormatter.ofPattern("HH:mm:ss")));

                this.forecastRepository.saveAndFlush(forecastToSave);

                sb.append(String.format(VALID_FORECAST,
                        forecastDto.getDayOfWeek(),
                        forecastDto.getMaxTemperature()));
                sb.append(System.lineSeparator());
            } else {
                appendErrorToStringBuilder(sb);
            }

        }

        return sb.toString().trim();
    }

    private static void appendErrorToStringBuilder(StringBuilder sb) {
        sb.append(INVALID_FORECAST).append(System.lineSeparator());
    }

    @Override
    public String exportForecasts() {
        Set<Forecast> forecasts = this.forecastRepository
                .findAllByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(
                        DayOfWeek.SUNDAY, 150000)
                .orElseThrow(NoSuchElementException::new);

        return forecasts.stream()
                .map(forecast -> String.format(PRINT_FORECAST_FORMAT,
                        forecast.getCity().getName(),
                        forecast.getMinTemperature(),
                        forecast.getMaxTemperature(),
                        forecast.getSunrise(),
                        forecast.getSunset()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
