package Spring.JPA.library.jpa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

//Definido a minha entidade.
@Entity
@Table(name="author", schema = "public") //Nao é obrigatorio, mas é bom por caso voce tem uma estrutura de esquemas no seu banco
@Getter // Anotação para gerar getter's e setter's
@Setter // Anotação para gerar getter's e setter's
@ToString(exclude = "books")
@EntityListeners(AuditingEntityListener.class)
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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Um autor, para muitos livros.
    private List<Book> books;

    @CreatedDate // Criar automatico a data de criação
    @Column(name = "create_at")
    private LocalDateTime create_at;

    @LastModifiedDate // Criar automatico a data de atualização
    @Column(name = "update_at")
    private LocalDateTime update_at;


    @Column(name = "id_user")
    private UUID idUser;

}
