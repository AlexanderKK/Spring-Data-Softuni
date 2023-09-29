package bg.softuni.workshop.constants;

import java.nio.file.Path;

public enum FilePaths {

    ;
    public static final Path IMPORT_COMPANIES_XML_PATH =
            Path.of("src", "main", "resources", "files", "xmls", "companies.xml");
    public static final Path IMPORT_PROJECTS_XML_PATH =
            Path.of("src", "main", "resources", "files", "xmls", "projects.xml");

    public static final Path IMPORT_EMPLOYEES_XML_PATH =
            Path.of("src", "main", "resources", "files", "xmls", "employees.xml");

}
