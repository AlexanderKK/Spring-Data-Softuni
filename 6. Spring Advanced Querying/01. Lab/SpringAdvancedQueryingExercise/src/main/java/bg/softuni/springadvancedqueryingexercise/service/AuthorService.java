package bg.softuni.springadvancedqueryingexercise.service;

import bg.softuni.springadvancedqueryingexercise.model.entity.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

//    List<String> getAllAuthorsOrderByCountOfTheirBooks();
}
