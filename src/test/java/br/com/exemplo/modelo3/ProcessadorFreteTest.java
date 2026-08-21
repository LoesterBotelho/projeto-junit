package br.com.exemplo.modelo3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessadorFreteTest {

    @Mock
    private ServicoFreteExterno servicoFreteMock; // Nome do mock

    @InjectMocks
    private ProcessadorFrete processadorFrete;

    @Test
    @DisplayName("Deve somar o valor do produto com a taxa de frete do estado de SP")
    void deveCalcularFreteParaSaoPaulo() {
        // CORRETO: Usando o nome exato da variável mockada (servicoFreteMock)
        when(servicoFreteMock.consultarTaxa("SP")).thenReturn(15.0);

        double resultado = processadorFrete.calcularValorFinalComFrete(100.0, "SP");

        assertEquals(115.0, resultado);
        
        // CORRETO: Usando servicoFreteMock aqui também
        verify(servicoFreteMock, times(1)).consultarTaxa("SP");
    }
}