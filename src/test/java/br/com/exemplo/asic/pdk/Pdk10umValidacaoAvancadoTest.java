package br.com.exemplo.asic.pdk;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes Avançados do PDK Proprietário de 10um")
class Pdk10umValidacaoAvancadoTest {

    static Stream<CelulaAsic> fornecerCelulas() {
        return Stream.of(
            new NandCell10um(),
            new NotCell10um(),
            new NorCell10um(),
            new AndCell10um()
        );
    }

    @Test
    @DisplayName("Deve garantir comprimento mínimo de canal de 10um (L=10) para transistores NMOS e PMOS")
    void deveValidarComprimentoMinimoGlobal() {
        Nmos nmos = new Nmos("N_TEST", 20, 10, 0, 0, "d", "g", "s", "b");
        Pmos pmos = new Pmos("P_TEST", 40, 10, 0, 0, "d", "g", "s", "b");

        assertAll(
            () -> assertEquals(10, nmos.lUf(), "NMOS deve respeitar L=10um"),
            () -> assertEquals(10, pmos.lUf(), "PMOS deve respeitar L=10um")
        );
    }

    @ParameterizedTest
    @DisplayName("Parametrizado: Todas as células devem retornar um nome válido e não nulo")
    @MethodSource("fornecerCelulas")
    void deveTerNomeValido(CelulaAsic celula) {
        String nome = celula.getNome();
        assertNotNull(nome, "O nome da célula não pode ser nulo.");
        assertFalse(nome.isBlank(), "O nome da célula não pode estar em branco.");
        assertTrue(nome.endsWith("_10um"), "O nome da célula deve seguir o sufixo padrão do PDK de 10um.");
    }

    @ParameterizedTest
    @DisplayName("Parametrizado: Netlists SPICE devem conter comandos essenciais de simulação e diretivas .subckt")
    @MethodSource("fornecerCelulas")
    void deveConterEstruturaSpicePadrao(CelulaAsic celula) {
        String spice = celula.gerarNetlistCompleta();

        assertAll(
            () -> assertTrue(spice.contains(".subckt " + celula.getNome()), "Deve conter a declaração .subckt da célula."),
            () -> assertTrue(spice.contains(".model nmos"), "Deve conter o modelo SPICE para NMOS."),
            () -> assertTrue(spice.contains(".model pmos"), "Deve conter o modelo SPICE para PMOS."),
            () -> assertTrue(spice.contains(".tran"), "Deve conter o comando de análise transitória (.tran)."),
            () -> assertTrue(spice.contains(".end"), "Deve conter a diretiva de encerramento .end.")
        );
    }

    @ParameterizedTest
    @DisplayName("Parametrizado: Geometrias geradas não podem possuir dimensões de caixa delimitadora invertidas")
    @MethodSource("fornecerCelulas")
    void deveValidarIntegridadeDasCoordenadasGeometricas(CelulaAsic celula) {
        List<GeometriaCamada> layout = celula.gerarLayoutFisico();

        assertNotNull(layout, "A lista de layout não pode ser nula.");
        assertFalse(layout.isEmpty(), "A lista de layout não pode estar vazia.");

        for (GeometriaCamada geom : layout) {
            assertAll(
                () -> assertNotNull(geom.camada(), "A camada geométrica deve ser informada."),
                () -> assertTrue(geom.x2() >= geom.x1(), "Coordenada X2 deve ser maior ou igual a X1 na camada: " + geom.camada()),
                () -> assertTrue(geom.y2() >= geom.y1(), "Coordenada Y2 deve ser maior ou igual a Y1 na camada: " + geom.camada())
            );
        }
    }

    @Test
    @DisplayName("Deve validar a presença específica de Metal1 nas interconexões")
    void deveValidarCamadasDeMetalizacao() {
        var celulas = fornecerCelulas().toList();

        for (CelulaAsic celula : celulas) {
            var layout = celula.gerarLayoutFisico();
            boolean possuiMetal1 = layout.stream().anyMatch(c -> c.camada().equalsIgnoreCase("METAL1"));
            
            assertTrue(possuiMetal1, "A célula " + celula.getNome() + " precisa conter rotas na camada METAL1.");
        }
    }
}