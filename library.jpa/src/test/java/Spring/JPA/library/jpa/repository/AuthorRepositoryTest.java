package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.model.Book;
import Spring.JPA.library.jpa.model.GenreBook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest // Essa anotation serve para subir o context do Spring Boot, classes e etc..
public class AuthorRepositoryTest {

    @Autowired // Injetamos o repository
    AuthorRepository repository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void SaveTest() {
        Author author = new Author(); // instanciado o objt novo autor
        author.setName("Matheus"); // Incluindo o nome.
        author.setNationality("Germany"); // incluindo nacionalidade
        author.setDateOfBitrh(LocalDate.of(1998, 10, 2)); // Data de nascimento

        var authorSaved =  repository.save(author); // Temos esse objeto com essas caracteristicas, agora, falta salvar no banco.
        System.out.println("Author saved: " + authorSaved);
    }

    @Test
    public void updateTeste(){
         var id = UUID.fromString("145de23e-ec24-4c01-8f0c-04e6476c9135");

         Optional<Author> maybeAuthor = repository.findById(id);

         if(maybeAuthor.isPresent()){

             Author foundAuthor = maybeAuthor.get();
             System.out.println("data of Author: ");
             System.out.println(foundAuthor);

             foundAuthor.setDateOfBitrh(LocalDate.of(1998, 10, 2));
             repository.save(foundAuthor);
         }
    }

    @Test
    public void listTest() {
        List<Author> list = repository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Count of authors: " + repository.count());
    }

    @Test
    public void deleteForTest() {
        var id = UUID.fromString("145de23e-ec24-4c01-8f0c-04e6476c9135");
        repository.deleteById(id);
    }

    @Test
    public void deleteForObjectTest() {
        var id = UUID.fromString("c111a00d-8d36-4bf4-b389-04357b839e7c");
        var jose =  repository.findById(id).get();
        repository.delete(jose);
    }

    @Test
    void saveAuthorWithBookTest(){
        Author author = new Author();
        author.setName("Inácio Felicio");
        author.setNationality("Autraliano");
        author.setDateOfBitrh(LocalDate.of(1702,4,2));

        Book book = new Book();
        book.setIsbn("1564-5552");
        book.setGenre(GenreBook.BIOGRAFIA);
        book.setPrice(BigDecimal.valueOf(140.58));
        book.setTitle("A coragem de ser imperfeito");
        book.setPublicationDate(LocalDate.of(1803,4,1));
        book.setAuthor(author);

        Book book2 = new Book();
        book2.setIsbn("23232-124576");
        book2.setGenre(GenreBook.FICCAO);
        book2.setPrice(BigDecimal.valueOf(10.99));
        book2.setTitle("The Witcher, Wild Hunt");
        book2.setPublicationDate(LocalDate.of(2000,8,5));
        book2.setAuthor(author);

        author.setBooks(new ArrayList<>());
        author.getBooks().add(book);
        author.getBooks().add(book2);

        authorRepository.save(author);
        bookRepository.saveAll(author.getBooks());
    }

    @Test
    //@Transactional
    void ListBookAuthorTest(){
        var id = UUID.fromString("8393659b-d7c9-4639-aa5e-35b25b1a4851");
        var author = repository.findById(id).get();

        List<Book> booklist = bookRepository.findByAuthor(author);
        author.setBooks(booklist);

        author.getBooks().forEach(System.out::println);
    }
}
