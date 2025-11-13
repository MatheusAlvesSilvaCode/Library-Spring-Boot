package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.model.Book;
import Spring.JPA.library.jpa.model.GenreBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository repository;

    @Test
    void salvarTest(){
        Book book = new Book();

        book.setIsbn("23526-56525");
        book.setPrice(BigDecimal.valueOf(130));
        book.setGenre(GenreBook.HORROR);
        book.setTitle("Harry Potter");
        book.setPublicationDate(LocalDate.of(2002,12,10));

        Author author = authorRepository
                .findById(UUID.fromString("89f299f2-bf63-4f63-a660-c01ff7ee581e"))
                .orElse(null);

        book.setAuthor(author);
        repository.save(book);

    }

}