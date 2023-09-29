package bg.softuni.springadvancedqueryingexercise.repository;

import bg.softuni.springadvancedqueryingexercise.model.entity.Book;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

//    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    @Query("SELECT COUNT(b) FROM Book AS b WHERE CHAR_LENGTH(b.title) > :number")
    Long countBookByTitleLengthGreaterThan(int number);

    List<Book> findAllByReleaseDateGreaterThanEqual(LocalDate date);

    @Query("UPDATE Book AS b SET b.copies = b.copies + :numberOfCopies WHERE b.releaseDate > :date")
    @Modifying
    int updateBookCopies(LocalDate date, int numberOfCopies);

//    @Procedure(procedureName = "Book.getTotalBooks" /* usp_get_total_books(full_name) */)
    @Query(value = "CALL spring_intro_exercise.usp_get_total_books(:full_name);", nativeQuery = true)
    int getTotalBooks(@Param("full_name") String fullName);

}
