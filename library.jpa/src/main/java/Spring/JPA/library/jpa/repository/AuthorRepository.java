package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
                                                    //Author, tipo de entidade. UUID é a Chave primária de Author
public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
