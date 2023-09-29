package bg.softuni.springautomappingobjects;

import bg.softuni.springautomappingobjects.entities.dtos.addresses.CreateAddressDTO;
import bg.softuni.springautomappingobjects.entities.dtos.CompanyDTO;
import bg.softuni.springautomappingobjects.entities.dtos.CreateEmployeeDTO;
import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

//@Component
public class JsonTestMain implements CommandLineRunner {

    private final Scanner scanner;
    private final Gson gson;

    @Autowired
    public JsonTestMain(Scanner scanner) {
        this.scanner = scanner;

        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("YYYY-MM-dd")
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDate.class,
                        (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                                LocalDate.parse(json.getAsString(),
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH)))
                .setPrettyPrinting()
                .create();
    }

    /**
        {
          "country": "Bulgaria",
          "city": "Burgas"
        }
        {
          "firstName": "First",
          "salary": 1,
          "address": {
            "country": "Bulgaria",
            "city": "Burgas"
          }
        }
    */

    static class LocalDateAdapter implements JsonSerializer<LocalDate> {

        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }

    }

    @Override
    public void run(String... args) throws Exception {


//        AddressDTO addressDTO = new AddressDTO("Bulgaria", "Burgas");
//
//        String jsonAddress = gson.toJson(addressDTO);

//        System.out.println(jsonAddress);

        //--------------------------------

//        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
//                "First", "Last", BigDecimal.ONE, LocalDate.now(), addressDTO);
//
//        String jsonEmployee = gson.toJson(createEmployeeDTO);
//
//        System.out.println(jsonEmployee);

        //--------------------------------

//        String input = this.scanner.nextLine();

//        CreateEmployeeDTO parsedEmployeeDTO = gson.fromJson(input, CreateEmployeeDTO.class);

//        System.out.println(parsedEmployeeDTO);

        //--------------------------------

//        AddressDTO addressDTO1 = new AddressDTO("Bulgaria", "Burgas");
//        AddressDTO addressDTO2 = new AddressDTO("Bulgaria", "Varna");
//        AddressDTO addressDTO3 = new AddressDTO("Bulgaria", "Ruse");

//        List<AddressDTO> addressDTOList = List.of(addressDTO1, addressDTO2, addressDTO3);
//
//        System.out.println(gson.toJson(addressDTOList));
//
//        String input = this.scanner.nextLine();
//
//        AddressDTO[] addressDTOS = gson.fromJson(input, AddressDTO[].class);
//
//        CreateEmployeeDTO[] employeesDTOs = gson.fromJson(input, CreateEmployeeDTO[].class);

        //--------------------------------

        // EmployeeDTO -> JSON

//        AddressDTO addressDTO1 = new AddressDTO("Bulgaria", "Burgas");

//        CreateEmployeeDTO createEmployeeDTO = new CreateEmployeeDTO(
//                "First", "Last", BigDecimal.ONE, LocalDate.now(), addressDTO1);
//
//        String json = gson.toJson(createEmployeeDTO);
//
//        System.out.println(json);
//
//        // JSON -> EmployeeDTO
//
//        String input = this.scanner.nextLine();
//        CreateEmployeeDTO jsonEmployeeDTO = gson.fromJson(input, CreateEmployeeDTO.class);
//
//        System.out.println(jsonEmployeeDTO);

        //--------------------------------

//        CreateAddressDTO createAddressDTO1 = new CreateAddressDTO("Bulgaria", "Burgas");
//        CreateEmployeeDTO employeeDTO1 = new CreateEmployeeDTO(
//                "First", "Last", BigDecimal.ONE, LocalDate.now(), createAddressDTO1);
//
//        CreateAddressDTO createAddressDTO2 = new CreateAddressDTO("Bulgaria", "Varna");
//        CreateEmployeeDTO employeeDTO2 = new CreateEmployeeDTO(
//                "Second", "Last", BigDecimal.TEN, LocalDate.now(), createAddressDTO2);
//
//        CreateAddressDTO createAddressDTO3 = new CreateAddressDTO("Bulgaria", "Ruse");
//        CreateEmployeeDTO employeeDTO3 = new CreateEmployeeDTO(
//                "Third", "Last", BigDecimal.ZERO, LocalDate.now(), createAddressDTO3);
//
//
//        CompanyDTO mega = new CompanyDTO("Mega", Set.of(employeeDTO1, employeeDTO2, employeeDTO3));
//
//        String json = this.gson.toJson(mega);
//
//        System.out.println(json);
//
//        String input = this.scanner.nextLine();
//        CompanyDTO parsedCompanyDTO = this.gson.fromJson(input, CompanyDTO.class);
//
//        System.out.printf("# Employees Cities From JSON String: #%n%s%n", json);
//        Set<CreateEmployeeDTO> employees = parsedCompanyDTO.getEmployees();
//        employees.forEach(e -> System.out.println(e.getAddress().getCity()));

    }

}
