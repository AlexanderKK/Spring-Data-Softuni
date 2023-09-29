package bg.softuni.productsshop.services.seed;

import static bg.softuni.productsshop.common.ConstantPaths.*;

import bg.softuni.productsshop.domain.dtos.CreateCategoryDTO;
import bg.softuni.productsshop.domain.dtos.CreateProductDTO;
import bg.softuni.productsshop.domain.dtos.CreateUserDTO;
import bg.softuni.productsshop.domain.entities.Category;
import bg.softuni.productsshop.domain.entities.Product;
import bg.softuni.productsshop.domain.entities.User;
import bg.softuni.productsshop.repositories.CategoryRepository;
import bg.softuni.productsshop.repositories.ProductRepository;
import bg.softuni.productsshop.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Gson gson;
    private final ModelMapper mapper;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository, Gson gson, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public String seedUsers() throws IOException {
        if(userRepository.count() > 0) {
            return "Users already seeded.";
        }

        File resource = new ClassPathResource(RESOURCE_FILE_URL_USERS).getFile();
        String userData = new String(Files.readAllBytes(resource.toPath()));

        List<CreateUserDTO> createUserDTOs =
                Arrays.stream(gson.fromJson(userData, CreateUserDTO[].class)).toList();

        for (CreateUserDTO createUserDTO : createUserDTOs) {
            User user = mapper.map(createUserDTO, User.class);

            userRepository.save(user);
        }

        return "Users seeded successfully.";
    }

    @Override
    public String seedProducts() throws IOException {
        if(productRepository.count() > 0) {
            return "Products already seeded.";
        }

        File resource = new ClassPathResource(RESOURCE_FILE_URL_PRODUCTS).getFile();
        String productsData = new String(Files.readAllBytes(resource.toPath()));

        List<CreateProductDTO> createProductDTOs =
                Arrays.stream(gson.fromJson(productsData, CreateProductDTO[].class)).toList();

        List<Product> productsToSave = new ArrayList<>();
        for (CreateProductDTO createProductDTO : createProductDTOs) {
            Product product = mapper.map(createProductDTO, Product.class);

            User randomBuyer = getRandomBuyer()[0];
            User randomSeller = getRandomBuyer()[1];

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

    @Override
    public String seedCategories() throws IOException {
        if(categoryRepository.count() > 0) {
            return "Categories already seeded.";
        }

        File resource = new ClassPathResource(RESOURCE_FILE_URL_CATEGORIES).getFile();
        String categoriesData = new String(Files.readAllBytes(resource.toPath()));

        List<Category> categories =
                Arrays.stream(gson.fromJson(categoriesData, CreateCategoryDTO[].class))
                .map(createCategoryDTO -> mapper.map(createCategoryDTO, Category.class))
                .toList();

        this.categoryRepository.saveAll(categories);

        return "Categories seeded successfully.";
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

    private User[] getRandomBuyer() {
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
