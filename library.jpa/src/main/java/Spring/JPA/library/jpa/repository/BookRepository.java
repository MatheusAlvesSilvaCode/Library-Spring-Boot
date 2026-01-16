package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.model.Book;
import Spring.JPA.library.jpa.model.GenreBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    // select * from book where genre = ? and price = ?
    List<Book> findByGenreAndPrice(GenreBook genre, BigDecimal price);

    // select * from book where title = ? or genre = ?
    List<Book> findByTitleOrGenre(String title, GenreBook genre);

    // JPQL -> referencia as entidades e as propriedades.
    //  select.* from book order by l.titulo, price
    @Query(" select l from Book as l order by l.title, l.price ")
    List<Book> ListAllBooksforTitleandPrice();


    /*
    * SELECT b.id, b.title as name_book, a.name as name_author
    from book as b
    join author as a on a.id = b.id_author
    */
    @Query("select a from Book b join b.author a ")
    List<Author> ListauthorofBook();


    // select distinct l.* from book l
    @Query("select distinct b.title from Book b")
    List<String> listOtherBooks();


    //Para fazer querys grandes, use tres aspas simples para conseguir quebrar as linhas.
    @Query("""
    select b.genre
    from Book b 
    join b.author a 
    where a.nationality = "Gothamita"
    order by b.genre
""")
    List<String> ListSAuthorGothamita();

    //Named Parameters -> Parametros nomeados
    @Query("select b from Book b where b.genre = :genre order by :ParaOrder")
    List<Book> FindByGenre(@Param("genre") GenreBook genreBook,
                           @Param("ParaOrder") String Nomepropriedade);


    //Positionals -> Parametro por posição
    @Query("select b from Book b where b.genre = ?2 order by ?1")
    List<Book> FindByGenrePositionalsParameters(String Nomepropriedade, GenreBook genreBook );

    @Modifying // É preciso quando vai fazer qualquer operação que faça mudança de registro, não apenas leitura.
    @Transactional
    @Query("delete from Book where genre = ?1")
    void DeleteByGenre(GenreBook genreBook);

    @Modifying
    @Transactional
    @Query("update Book b set b.publicationDate = ?1 where b.id = ?2")
    void updtaDatePublication(LocalDate publicationDate, UUID id);
}
