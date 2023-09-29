package bg.softuni.springdataintroexercise.services.book;

import bg.softuni.springdataintroexercise.models.Book;
import bg.softuni.springdataintroexercise.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAll(books);
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public List<Book> getAllByReleaseDateAfter(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateAfter(date)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> getAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(String firstname, String lastname) {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstname, lastname)
                .orElseThrow(NoSuchElementException::new);
    }

}
