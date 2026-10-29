package br.com.exemplo.receitas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

class DetectorFraudeTest {

    private final DetectorFraude detector = new DetectorFraude();

    @Test
    void deveDetectarNumeroDuplicado() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 27), true);

        var registrada = criarReceita(
                2L, "SC-100", "hash-2", LocalDate.of(2026, 10, 26), true);

        var indicios = detector.detectar(
                receita, List.of(registrada), LocalDate.of(2026, 10, 28));

        assertTrue(indicios.contains(IndicioFraude.NUMERO_DUPLICADO));
    }

    @Test
    void deveDetectarHashDocumentoRepetido() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 27), true);

        var registrada = criarReceita(
                2L, "SC-200", "hash-1", LocalDate.of(2026, 10, 26), true);

        var indicios = detector.detectar(
                receita, List.of(registrada), LocalDate.of(2026, 10, 28));

        assertTrue(indicios.contains(IndicioFraude.HASH_DOCUMENTO_REPETIDO));
    }

    @Test
    void deveDetectarDataFutura() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 29), true);

        var indicios = detector.detectar(
                receita, List.of(), LocalDate.of(2026, 10, 28));

        assertTrue(indicios.contains(IndicioFraude.DATA_FUTURA));
    }

    @Test
    void deveDetectarReceitaReutilizada() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 27), true);

        receita.marcarComoUtilizada();

        var indicios = detector.detectar(
                receita, List.of(), LocalDate.of(2026, 10, 28));

        assertTrue(indicios.contains(IndicioFraude.RECEITA_REUTILIZADA));
    }

    @Test
    void deveDetectarAssinaturaInvalida() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 27), false);

        var indicios = detector.detectar(
                receita, List.of(), LocalDate.of(2026, 10, 28));

        assertTrue(indicios.contains(IndicioFraude.ASSINATURA_INVALIDA));
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremIndicios() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 27), true);

        var registrada = criarReceita(
                2L, "SC-200", "hash-2", LocalDate.of(2026, 10, 26), true);

        var indicios = detector.detectar(
                receita, List.of(registrada), LocalDate.of(2026, 10, 28));

        assertTrue(indicios.isEmpty());
    }

    @Test
    void deveRetornarIndiciosSemDuplicatas() {
        var receita = criarReceita(
                1L, "SC-100", "hash-1", LocalDate.of(2026, 10, 27), true);

        var primeira = criarReceita(
                2L, "SC-100", "hash-1", LocalDate.of(2026, 10, 26), true);

        var segunda = criarReceita(
                3L, "SC-100", "hash-1", LocalDate.of(2026, 10, 25), true);

        var indicios = detector.detectar(
                receita, List.of(primeira, segunda), LocalDate.of(2026, 10, 28));

        assertEquals(
                indicios.size(),
                indicios.stream().distinct().count());
    }

    private Receita criarReceita(
            Long id,
            String numero,
            String hash,
            LocalDate dataEmissao,
            boolean assinaturaValida) {

        var paciente = new Paciente(
                1L,
                "Ana Silva",
                "12345678901",
                LocalDate.of(1995, 5, 12));

        var doutor = new Doutor(
                1L,
                "Roberto Lima",
                "123456",
                "SC");

        return new Receita(
                id,
                numero,
                TipoReceita.BRANCA,
                paciente,
                doutor,
                dataEmissao,
                hash,
                assinaturaValida);
    }
    
}