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

        book.setIsbn("257886-0303");
        book.setPrice(BigDecimal.valueOf(99));
        book.setGenre(GenreBook.FANTASIA);
        book.setTitle("Dune");
        book.setPublicationDate(LocalDate.of(196,2,4));

        Author author = authorRepository
                .findById(UUID.fromString("89f299f2-bf63-4f63-a660-c01ff7ee581e"))
                .orElse(null);

        book.setAuthor(author);
        repository.save(book);

    }

    @Test
    void salvarCascadeTest(){
        Book book = new Book();
        book.setIsbn("257886-0303");
        book.setPrice(BigDecimal.valueOf(99));
        book.setGenre(GenreBook.FANTASIA);
        book.setTitle("Dune");
        book.setPublicationDate(LocalDate.of(196,2,4));

        Author author = new Author(); // instanciado o objt novo autor
        author.setName("Pedro Verissimo"); // Incluindo o nome.
        author.setNationality("Portugues"); // incluindo nacionalidade
        author.setDateOfBitrh(LocalDate.of(1976, 05, 1)); // Data de nascimento

        book.setAuthor(author);
        repository.save(book);

    }

    @Test
    void salveAuthorAndBook(){

        Book book = new Book();
        book.setIsbn("255586-0202");
        book.setPrice(BigDecimal.valueOf(79));
        book.setGenre(GenreBook.MISTERIO);
        book.setTitle("Memory");
        book.setPublicationDate(LocalDate.of(2000,5,7));

        Author author = new Author(); // instanciado o objt novo autor
        author.setName("Manoel Gomes"); // Incluindo o nome.
        author.setNationality("Itallian"); // incluindo nacionalidade
        author.setDateOfBitrh(LocalDate.of(1756, 8, 6)); // Data de nascimento

        authorRepository.save(author);
        book.setAuthor(author);
        repository.save(book);
    }
}