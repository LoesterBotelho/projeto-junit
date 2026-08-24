package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class RepositorioContabilidadeTest {
    @Test
    void deveSalvarEBuscarConta() {
        RepositorioContabilidade repo = new RepositorioContabilidade();
        ContaContabil conta = new ContaContabil("3.1", "Despesa", TipoNaturezaConta.DEVEDORA, true);
        repo.salvarConta(conta);
        
        Optional<ContaContabil> opt = repo.buscarConta("3.1");
        assertTrue(opt.isPresent());
        assertEquals("Despesa", opt.get().getNome());
    }
}