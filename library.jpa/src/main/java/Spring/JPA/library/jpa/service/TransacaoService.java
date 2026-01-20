package Spring.JPA.library.jpa.service;

import Spring.JPA.library.jpa.model.Author;
import Spring.JPA.library.jpa.model.Book;
import Spring.JPA.library.jpa.model.GenreBook;
import Spring.JPA.library.jpa.repository.AuthorRepository;
import Spring.JPA.library.jpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void UpdateWithoutUpdate(){
    var book = bookRepository.findById(UUID.fromString("90f65f12-5443-443d-ae49-911ccc3f69ba"))
            .orElse(null);

    book.setGenre(GenreBook.BIOGRAFIA);
    //bookRepository.save(book); "Não precisa dessa linha, pois ja temos uma transação aberta."
    }


    @Transactional
    public void executar(){
        //Save Author
        Author  author = new Author();
        author.setName("Sun Tzu");
        author.setNationality("Chines");
        author.setDateOfBitrh(LocalDate.of(2006,3,27));

        authorRepository.save(author);


        //Save Book
        Book book = new Book();
        book.setIsbn("23654-56985");
        book.setPrice(BigDecimal.valueOf(769.33));
        book.setGenre(GenreBook.BIOGRAFIA);
        book.setTitle("A arte da Guerra");
        book.setPublicationDate(LocalDate.of(1925,4,5));

        book.setAuthor(author);

        bookRepository.save(book);

        //Tratamento de erros
        if(author.getName().equals("Alfred Pennyworf")){
            throw  new RuntimeException("Fizemos um RollBack aqui!!!");
        }
    }

}
