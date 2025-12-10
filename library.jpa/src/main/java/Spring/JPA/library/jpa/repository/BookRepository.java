package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.model.Book;
import Spring.JPA.library.jpa.model.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    //Query Method
    // select * from author where id_author = id
    List<Book> findByAuthor(Author author);


    //select * from book where title = title
    List<Book> findByTitle(String title);

    //select * from book where isbn = isbn
    List<Book> findByIsbn(String isbn);

    //select * from book where = price = price
    List<Book> findByPrice(BigDecimal price);

    // select * from book where genre = genre
    List<Book> findByGenre(GenreBook genre);

    List<Book> findByGenreAndPrice(GenreBook genre, BigDecimal price);

    List<Book> findByTitleOrGenre(String title, GenreBook genre);

}
