package bg.softuni.springadvancedqueryingexercise.service;

import bg.softuni.springadvancedqueryingexercise.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    Long countBookByTitleLengthGreaterThan(int number);

    List<Book> findAllBooksAfterDate(LocalDate date);

    int updateBookCopies(LocalDate date, int numberOfCopies);

    int getTotalBooks(String fullName);

}
