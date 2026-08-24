package br.com.exemplo.contabil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicoConsultaContabilTest {

    @Mock
    private RepositorioContabilidade repositorioMock;

    @InjectMocks
    private ServicoConsultaContabil servicoConsulta;

    @Test
    void deveRetornarContaQuandoExistir() {
        ContaContabil contaEsperada = new ContaContabil("1.1", "Caixa", TipoNaturezaConta.DEVEDORA, true);
        when(repositorioMock.buscarConta("1.1")).thenReturn(Optional.of(contaEsperada));

        ContaContabil resultado = servicoConsulta.obterContaPorCodigo("1.1");
        assertNotNull(resultado);
        assertEquals("Caixa", resultado.getNome());
        verify(repositorioMock, times(1)).buscarConta("1.1");
    }

    @Test
    void deveLancarExcecaoQuandoContaNaoExistir() {
        when(repositorioMock.buscarConta("9.9")).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> servicoConsulta.obterContaPorCodigo("9.9"));
        verify(repositorioMock, times(1)).buscarConta("9.9");
    }
}