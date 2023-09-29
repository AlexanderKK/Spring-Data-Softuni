package bg.softuni.springdataintroexercise.services.author;

import bg.softuni.springdataintroexercise.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();

    Author getRandomAuthor();

    List<Author> getDistinctByBooksBefore(LocalDate date);

    List<Author> getAllGroupByBooksOrderByCount();

}
