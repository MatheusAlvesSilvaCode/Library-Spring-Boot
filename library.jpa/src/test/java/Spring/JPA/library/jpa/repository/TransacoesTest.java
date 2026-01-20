package Spring.JPA.library.jpa.repository;

import Spring.JPA.library.jpa.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit = Confirmar alterqações
     * RollBack = Desfazer Altereções
     */
    @Test
    void TransacaoSimples(){
    transacaoService.executar();
    }

    @Test
    void TransacaoSimplesGenre(){
        transacaoService.UpdateWithoutUpdate();
    }
}
