package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.model.Book;
import Spring.JPA.library.jpa.model.GenreBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see BookRepositoryTest
 * */

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository repository;

    @Test
    void saveTest(){
        Book book = new Book();

        book.setIsbn("0020121-5694554");
        book.setPrice(BigDecimal.valueOf(45.33));
        book.setGenre(GenreBook.MISTERIO);
        book.setTitle("Lisboa Noir");
        book.setPublicationDate(LocalDate.of(2003,7,1));

        Author author = authorRepository
                .findById(UUID.fromString("89f299f2-bf63-4f63-a660-c01ff7ee581e"))
                .orElse(null);

        book.setAuthor(author);
        repository.save(book);

    }

    @Test
    void saveCascadeTest(){
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
    void saveAuthorAndBook(){

        Book book = new Book();
        book.setIsbn("369852-458565");
        book.setPrice(BigDecimal.valueOf(430.63));
        book.setGenre(GenreBook.BIOGRAFIA);
        book.setTitle("A destruição de Marineford");
        book.setPublicationDate(LocalDate.of(2008,4,12));

        Author author = new Author(); // instanciado o objt novo autor
        author.setName("Clarice Lispector"); // Incluindo o nome.
        author.setNationality("Suzanense"); // incluindo nacionalidade
        author.setDateOfBitrh(LocalDate.of(1998, 10, 3)); // Data de nascimento

        authorRepository.save(author);
        book.setAuthor(author);
        repository.save(book);
    }

    @Test
    void updateAuthorfromBook(){
        UUID id = UUID.fromString("8a596e25-052e-41dc-a2a9-1a1f75a45562");
        var bookforupdate = repository.findById(id).orElse(null);

        UUID id1 = UUID.fromString("89f299f2-bf63-4f63-a660-c01ff7ee581e"); // ID Author Matheus
        Author matheus = authorRepository.findById(id1).orElse(null);

        bookforupdate.setAuthor(matheus);
        System.out.println("Conteúdo variavel Matheus: " + matheus);
        repository.save(bookforupdate);

    }

    //Method for update book name with id book
    @Test
    void updateNamebook(){
        UUID id = UUID.fromString("d4edc68c-c1da-42a5-8306-0d3dc4dfbdbc"); // convert UUID for string.
        var bookforupdatename = repository.findById(id).orElse(null);
        var newname = "Gotham City, The Revenge";

        bookforupdatename.setTitle(newname);
        repository.save(bookforupdatename);
    }

    @Test
    void delete(){
        UUID id = UUID.fromString("8a0b9877-8cd5-4cc9-8368-5c8126a8133b");
        repository.deleteById(id);
    }

    @Test
    void deleteCascade(){
        UUID id = UUID.fromString("b661754a-929b-41e4-a3bc-a6642042af03");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void searchBookTest(){
        UUID id = UUID.fromString("3498c79b-ebf6-4bc8-be29-dbe5e5e1c203");
        Book book = repository.findById(id).orElse(null);
        System.out.println("BOOK");
        System.out.println(book.getTitle());

        System.out.println("AUTHOR");
        System.out.println(book.getAuthor().getName());
    }

    @Test
    void searchForTitleTest(){
        List<Book> list = repository.findByTitle("Harry Potter");
        list.forEach(System.out::println);
    }

    @Test
    void searchForIsbnTest(){
        List<Book> list = repository.findByIsbn("23232-124576");
        list.forEach(System.out::println);
    }

    @Test
    void serachForPriceTest(){
        List<Book> list = repository.findByPrice(BigDecimal.valueOf(59.99));
        list.forEach(System.out::println);
    }

    @Test
    void serachForGenreAndTitleTest(){
       List<Book> list = repository.findByGenreAndPrice(GenreBook.FANTASIA, BigDecimal.valueOf(59.99));
       list.forEach(System.out::println);
    }

    @Test
    void searchTitleOrGenreTest(){
        var title = "Maus";
        var genre = GenreBook.BIOGRAFIA;
        List<Book> list = repository.findByTitleOrGenre(title, genre);
        list.forEach(System.out::println);
    }

    @Test
    void ListbookWithJPQLQueryTest(){
        var result = repository.ListAllBooksforTitleandPrice();
        result.forEach(System.out::println);
    }

    @Test
    void ListAuthorsOfBookTest(){
        var result = repository.ListauthorofBook();
        result.forEach(System.out::println);
    }

    @Test
    void ListDistinctBooksTest(){
        var result = repository.listOtherBooks();
        result.forEach(System.out::println);
    }

    @Test
    void ListGothamitasAuthor(){
        var result = repository.ListSAuthorGothamita();
        result.forEach(System.out::println);
    }

    @Test
    void ListarPorGeneroParam(){
        var result = repository.FindByGenre(GenreBook.HORROR, "price");
        result.forEach(System.out::println);
    }

    @Test
    void ListarPorGeneroPositionals(){
        var result = repository.FindByGenrePositionalsParameters("price", GenreBook.HORROR);
        result.forEach(System.out::println);
    }

    @Test
    void DeleteForGenre(){
        repository.DeleteByGenre(GenreBook.BIOGRAFIA);
    }

    @Test
    void updateDatePublication()                               {
        UUID id = UUID.fromString("243b3ad4-ba66-4e5f-a568-1d9b3ffffe71"); //   convert UUID for string.
        repository.updtaDatePublication(LocalDate.of(2026,5,16), id);
    }
}