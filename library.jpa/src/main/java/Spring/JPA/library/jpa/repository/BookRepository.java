package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
