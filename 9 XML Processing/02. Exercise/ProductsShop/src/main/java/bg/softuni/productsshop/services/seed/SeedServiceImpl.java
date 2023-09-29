package bg.softuni.productsshop.services.seed;

import static bg.softuni.productsshop.common.ConstantPaths.*;

import bg.softuni.productsshop.domain.dtos.categories.wrappers.CategoriesImportWrapperDTO;
import bg.softuni.productsshop.domain.dtos.products.ProductImportDTO;
import bg.softuni.productsshop.domain.dtos.products.wrappers.ProductsImportWrapperDTO;
import bg.softuni.productsshop.domain.dtos.users.wrappers.UsersImportWrapperDTO;
import bg.softuni.productsshop.domain.entities.Category;
import bg.softuni.productsshop.domain.entities.Product;
import bg.softuni.productsshop.domain.entities.User;
import bg.softuni.productsshop.repositories.CategoryRepository;
import bg.softuni.productsshop.repositories.ProductRepository;
import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    @Qualifier(value = "usersContext")
    private final JAXBContext usersContext;

    @Qualifier(value = "categoriesContext")
    private final JAXBContext categoriesContext;

    private final JAXBContext productsContext;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository,
                           Gson gson,
                           ModelMapper mapper,
                           JAXBContext usersContext,
                           JAXBContext categoriesContext,
                           JAXBContext productsContext) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.mapper = mapper;
        this.usersContext = usersContext;
        this.categoriesContext = categoriesContext;
        this.productsContext = productsContext;
    }

    @Override
    public String seedUsers() throws IOException, JAXBException {
        if(userRepository.count() > 0) {
            return "Users already seeded.";
        }

//        File resource = new ClassPathResource(RESOURCE_FILE_URL_USERS).getFile();

//        UsersImportWrapperDTO usersDTO = (UsersImportWrapperDTO) unmarshaller.unmarshal(resource);

        FileReader fileReader = new FileReader(USERS_XML_PATH.toFile());

        Unmarshaller unmarshaller = usersContext.createUnmarshaller();

        UsersImportWrapperDTO usersDTO = (UsersImportWrapperDTO) unmarshaller.unmarshal(fileReader);

        List<User> users = usersDTO.getUsers()
                .stream()
                .map(userDTO -> mapper.map(userDTO, User.class))
                .toList();

        userRepository.saveAll(users);

        return "Users seeded successfully.";
    }

    @Override
    public String seedCategories() throws IOException, JAXBException {
        if(categoryRepository.count() > 0) {
            return "Categories already seeded.";
        }

        FileReader fileReader = new FileReader(CATEGORIES_XML_PATH.toFile());

        Unmarshaller unmarshaller = categoriesContext.createUnmarshaller();

        CategoriesImportWrapperDTO categoriesDTO = (CategoriesImportWrapperDTO) unmarshaller.unmarshal(fileReader);

        List<Category> categories = categoriesDTO.getCategories()
                .stream()
                .map(categoryDTO -> mapper.map(categoryDTO, Category.class))
                .collect(Collectors.toList());

        this.categoryRepository.saveAll(categories);

        return "Categories seeded successfully.";
    }

    @Override
    public String seedProducts() throws IOException, JAXBException {
        if(productRepository.count() > 0) {
            return "Products already seeded.";
        }

        BufferedReader reader = new BufferedReader(new FileReader(PRODUCTS_XML_PATH.toFile()));
        Unmarshaller unmarshaller = productsContext.createUnmarshaller();

        ProductsImportWrapperDTO productsDTO = (ProductsImportWrapperDTO) unmarshaller.unmarshal(reader);
        reader.close();

        List<Product> productsToSave = new ArrayList<>();
        for (ProductImportDTO productImportDTO : productsDTO.getProducts()) {
            Product product = mapper.map(productImportDTO, Product.class);

            User randomBuyer = getRandomUser()[0];
            User randomSeller = getRandomUser()[1];

            long randomNullBuyer = Math.abs(randomBuyer.getId() - randomSeller.getId()) / 5;
            if(randomBuyer.getId() % 2 != 0 && randomBuyer.getId() < randomNullBuyer) {
                product.setBuyer(null);
            } else {
                product.setBuyer(randomBuyer);
            }

            product.setSeller(randomSeller);

            setRandomCategories(product);

            productsToSave.add(product);
        }

        this.productRepository.saveAll(productsToSave);

        return "Products seeded successfully.";
    }

    private void setRandomCategories(Product product) {
        Random random = new Random();

        int numberOfCategories = random.nextInt((int) categoryRepository.count() + 1);

        Set<Category> categories = new HashSet<>();

        IntStream.range(1, numberOfCategories)
                .forEach(number -> {
                    Category category = this.categoryRepository.getRandomCategory();
                    categories.add(category);
                });

        product.setCategories(categories);
    }

    private User[] getRandomUser() {
        Random random = new Random();

        long min = 1L;
        long max = userRepository.count() + 1;

        long randomIdBuyer = random.nextLong(min, max);
        long randomIdSeller = random.nextLong(min, max);

        return new User[] {
                userRepository.findById(randomIdBuyer).get(),
                userRepository.findById(randomIdSeller).get()
        };
    }

}
