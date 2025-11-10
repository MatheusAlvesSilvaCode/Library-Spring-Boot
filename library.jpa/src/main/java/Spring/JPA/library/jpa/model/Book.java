package Spring.JPA.library.jpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "book")
@Data // Gera os getters e setters de forma aut. como construtores vazios e etc.
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", nullable = false, length = 50)
    private String isbn;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "publication_date", nullable = false, length = 150)
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = false, length = 30)
    private GenreBook genre;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    // Uma coluna que relaciona com Autor, devemos colocar o objeto nela.
    @JoinColumn(name = "id_author")
    @ManyToOne
    private Author author;

}
