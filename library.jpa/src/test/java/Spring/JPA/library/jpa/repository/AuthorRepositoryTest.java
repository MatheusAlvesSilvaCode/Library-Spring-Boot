package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest // Essa anotation serve para subir o context do Spring Boot, classes e etc..
public class AuthorRepositoryTest {

    @Autowired // Injetamos o repository
    AuthorRepository repository;

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
}
