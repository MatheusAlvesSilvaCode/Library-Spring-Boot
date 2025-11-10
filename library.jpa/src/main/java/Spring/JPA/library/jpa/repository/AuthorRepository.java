package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {

}
