package Spring.JPA.library.jpa.service;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author salve(Author author) {
        return repository.save(author);
    }
}
