package bg.softuni.springadvancedqueryingexercise;

import bg.softuni.springadvancedqueryingexercise.model.entity.Book;
import bg.softuni.springadvancedqueryingexercise.service.AuthorService;
import bg.softuni.springadvancedqueryingexercise.service.BookService;
import bg.softuni.springadvancedqueryingexercise.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

        //printAllBooksAfterYear(2000);
//        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
     //   printAllAuthorsAndNumberOfTheirBooks();
//        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
//        printAllBookDataWithReleaseDateBefore();
//        printCountBooksWithTitleLongerThan();
//        updateBookCopies();
        getTotalBooksByAuthorName();
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

//    private void printAllAuthorsAndNumberOfTheirBooks() {
//        authorService
//                .getAllAuthorsOrderByCountOfTheirBooks()
//                .forEach(System.out::println);
//    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBookDataWithReleaseDateBefore() {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

        bookService
                .findAllByReleaseDateBefore(date)
                .forEach(b -> {
                    System.out.printf("%s %s %.2f%n",
                            b.getTitle(), b.getEditionType(), b.getPrice());
                });
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void printCountBooksWithTitleLongerThan() {
        int number = Integer.parseInt(new Scanner(System.in).nextLine());

        Long result = this.bookService.countBookByTitleLengthGreaterThan(number);

        System.out.println(result);
    }

    private void updateBookCopies() {
        Scanner scanner = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(scanner.nextLine(), formatter);

        int numberOfCopies = Integer.parseInt(scanner.nextLine());

        int updatedBooksCount = bookService.updateBookCopies(date, numberOfCopies);

        System.out.println(updatedBooksCount * numberOfCopies);
    }

    private void getTotalBooksByAuthorName() {
        String authorName = new Scanner(System.in).nextLine();

        int totalBooks = this.bookService.getTotalBooks(authorName);

        System.out.println(totalBooks);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
