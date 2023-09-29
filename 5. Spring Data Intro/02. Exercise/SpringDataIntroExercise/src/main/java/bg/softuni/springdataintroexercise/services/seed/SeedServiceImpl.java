package bg.softuni.springdataintroexercise.services.seed;

import bg.softuni.springdataintroexercise.models.Author;
import bg.softuni.springdataintroexercise.models.Book;
import bg.softuni.springdataintroexercise.models.Category;
import bg.softuni.springdataintroexercise.models.Password;
import bg.softuni.springdataintroexercise.models.enums.AgeRestriction;
import bg.softuni.springdataintroexercise.models.enums.EditionType;
import bg.softuni.springdataintroexercise.services.author.AuthorService;
import bg.softuni.springdataintroexercise.services.book.BookService;
import bg.softuni.springdataintroexercise.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static bg.softuni.springdataintroexercise.constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(
            BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
//        BufferedReader bufferedReader = new BufferedReader(
//                new FileReader(RESOURCE_URL + AUTHOR_FILE_NAME));
//
//        String line;
//        while((line = bufferedReader.readLine()) != null) {
//            System.out.println(line);
//        }

        if(!this.authorService.isDataSeeded()) {
            List<Author> authorsData = Files.readAllLines(
                    Path.of(RESOURCE_URL + AUTHOR_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(s -> {
                        String[] firstAndLastName = s.split(" ");

                        Author author = Author.builder()
                                .firstName(firstAndLastName[0])
                                .lastName(firstAndLastName[1])
                                .build();

                        return author;
                    }).collect(Collectors.toList());

            this.authorService.seedAuthors(authorsData);
        }
    }

    @Override
    public void seedBooks() throws IOException {
        if(!this.bookService.isDataSeeded()) {
            final List<Book> booksData = Files.readAllLines(
                    Path.of(RESOURCE_URL, BOOK_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(line -> {
                        String[] data = line.split("\\s+");

                        String title = Arrays.stream(data)
                                .skip(5)
                                .collect(Collectors.joining(" "));

                        return Book.builder()
                                .title(title)
                                .editionType(EditionType.values()[Integer.parseInt(data[0])])
                                .price(new BigDecimal(data[3]))
                                .releaseDate(LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                                .ageRestriction(AgeRestriction.values()[Integer.parseInt(data[4])])
                                .author(this.authorService.getRandomAuthor())
                                .categories(this.categoryService.getRandomCategories())
                                .copies(Integer.parseInt(data[2]))
                                .build();
                    })
                    .collect(Collectors.toList());

            this.bookService.seedBooks(booksData);
        }
    }

    @Override
    public void seedCategory() throws IOException {
        if(!this.categoryService.isDataSeeded()) {
            List<Category> categoriesData = Files.readAllLines(
                            Path.of(RESOURCE_URL, CATEGORY_FILE_NAME))
                    .stream()
                    .filter(s -> !s.isBlank())
                    .map(name -> Category.builder()
                            .name(name)
                            .build())
                    .collect(Collectors.toList());

            this.categoryService.seedCategories(categoriesData);
        }
    }

}
