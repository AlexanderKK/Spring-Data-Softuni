package bg.softuni.springdataintroexercise.services.book;

import bg.softuni.springdataintroexercise.models.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks(List<Book> books);

    boolean isDataSeeded();

    List<Book> getAllByReleaseDateAfter(LocalDate date);

    List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstname, String lastname);

}
