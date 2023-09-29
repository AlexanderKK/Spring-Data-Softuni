package softuni.exam.constants;

import java.nio.file.Path;

public enum FilePaths {

    ;
    public static final Path IMPORT_COUNTRIES_JSON_PATH =
            Path.of("src", "main", "resources", "files", "json", "countries.json");
    public static final Path IMPORT_CITIES_JSON_PATH =
            Path.of("src", "main", "resources", "files", "json", "cities.json");
    public static final Path IMPORT_FORECASTS_XML_PATH =
            Path.of("src", "main", "resources", "files", "xml", "forecasts.xml");

}
