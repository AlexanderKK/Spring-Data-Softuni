package bg.softuni.workshop.services;

import bg.softuni.workshop.models.company.Company;
import bg.softuni.workshop.models.company.dto.ImportCompaniesWrapperDTO;
import bg.softuni.workshop.repositories.CompanyRepository;
import bg.softuni.workshop.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static bg.softuni.workshop.constants.FilePaths.IMPORT_COMPANIES_XML_PATH;

@Service
public class CompanyService {

    private final ModelMapper mapper;
    private final CompanyRepository companyRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public CompanyService(ModelMapper mapper, CompanyRepository companyRepository, ValidationUtil validationUtil) {
        this.mapper = mapper;
        this.companyRepository = companyRepository;
        this.validationUtil = validationUtil;
    }

    public String getXMLContent() throws IOException {
        return Files.readString(IMPORT_COMPANIES_XML_PATH);
    }

    public void importCompanies() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(ImportCompaniesWrapperDTO.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ByteArrayInputStream stream = new ByteArrayInputStream(getXMLContent().getBytes());

        ImportCompaniesWrapperDTO companiesDTO =
                (ImportCompaniesWrapperDTO) unmarshaller.unmarshal(stream);

        List<Company> companies = companiesDTO.getCompanies().stream()
                .filter(companyDTO -> {
                    boolean isValid = this.validationUtil.isValid(companyDTO);

                    System.out.println(isValid
                            ? String.format("Successfully imported company %s", companyDTO.getName())
                            : "Invalid company");

                    return isValid;
                })
                .map(companyDTO -> this.mapper.map(companyDTO, Company.class))
                .toList();

        this.companyRepository.saveAllAndFlush(companies);
    }

    public Boolean areImported() {
        return this.companyRepository.count() > 0;
    }

}
