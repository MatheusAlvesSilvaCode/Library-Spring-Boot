package Spring.JPA.library.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//Definido a minha entidade.
@Entity
@Table(name="author", schema = "public") //Nao é obrigatorio, mas é bom por caso voce tem uma estrutura de esquemas no seu banco
@Getter // Anotação para gerar getter's e setter's
@Setter // Anotação para gerar getter's e setter's


public class Author {

    @Deprecated
    public Author() {
        // Para uso do Framework
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID) // anotacao para o valor ser gerado automaticamente
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "dob", nullable = false)
    private LocalDate dateOfBitrh;

    @Column(name = "nationality", length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "author") // Um autor, para muitos livros.
    private List<Book> books;

}
