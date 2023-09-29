package bg.softuni.springdataintroexercise;

import bg.softuni.springdataintroexercise.models.Author;
import bg.softuni.springdataintroexercise.models.Book;
import bg.softuni.springdataintroexercise.services.author.AuthorService;
import bg.softuni.springdataintroexercise.services.book.BookService;
import bg.softuni.springdataintroexercise.services.seed.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    private LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedService.seedAllData();

        this.getAllBooksAfterAGivenYear();
        this.getAllAuthorsWithBooksReleaseDateBefore();
        this.getAllAuthorsOrderByBooksCount();
        this.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc();
    }

    private void getAllBooksAfterAGivenYear() {
        final List<Book> allByReleaseDateAfter = this.bookService.getAllByReleaseDateAfter(BOOK_YEAR_AFTER);

        allByReleaseDateAfter.stream().map(Book::getTitle).forEach(System.out::println);
    }

    private void getAllAuthorsWithBooksReleaseDateBefore() {
        List<Author> distinctAuthorsByBooksBefore = this.authorService.getDistinctByBooksBefore(BOOK_YEAR_BEFORE);
        distinctAuthorsByBooksBefore.forEach(author -> {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        });
    }

    private void getAllAuthorsOrderByBooksCount() {
        List<Author> authorsByBooksCount = this.authorService.getAllGroupByBooksOrderByCount();
        authorsByBooksCount.forEach(author ->
            System.out.printf("%s %s %d%n",
                    author.getFirstName(), author.getLastName(), author.getBooks().size())
        );
    }

    private void findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc() {
        this.bookService.getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc("George", "Powell")
                .forEach(book -> System.out.printf("%s %s %d%n",
                        book.getTitle(), book.getReleaseDate(), book.getCopies()));
    }

}
