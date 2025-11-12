package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest // Essa anotation serve para subir o context do Spring Boot, classes e etc..
public class AuthorRepositoryTest {

    @Autowired // Injetamos o repository
    AuthorRepository repository;

    @Test
    public void SalvarTest() {
        Author author = new Author(); // instanciado o objt novo autor
        author.setName("Matheus"); // Incluindo o nome.
        author.setNationality("Germany"); // incluindo nacionalidade
        author.setDateOfBitrh(LocalDate.of(1998, 10, 2)); // Data de nascimento

        var authorSaved =  repository.save(author); // Temos esse objeto com essas caracteristicas, agora, falta salvar no banco.
        System.out.println("Author saved: " + authorSaved);
    }

    @Test
    public void atualizarTeste(){
         var id = UUID.fromString("145de23e-ec24-4c01-8f0c-04e6476c9135");

         Optional<Author> maybeAuthor = repository.findById(id);

         if(maybeAuthor.isPresent()){

             Author foundAuthor = maybeAuthor.get();
             System.out.println("data of Author: ");
             System.out.println(foundAuthor);

             foundAuthor.setDateOfBitrh(LocalDate.of(1900, 9, 9));
             repository.save(foundAuthor);
         }
    }
}
