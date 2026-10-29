package br.com.exemplo.receitas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class DetectorFraude {

    public List<IndicioFraude> detectar(
    		
            Receita receita,
            List<Receita> receitasRegistradas,
            LocalDate dataReferencia) {

        Objects.requireNonNull(receita);
        Objects.requireNonNull(receitasRegistradas);
        Objects.requireNonNull(dataReferencia);

        var indicios = new ArrayList<IndicioFraude>();
        Set<String> numeros = new HashSet<>();
        Set<String> hashes = new HashSet<>();

        for (var registrada : receitasRegistradas) {
            if (Objects.equals(registrada.getId(), receita.getId())) {
                continue;
            }

            numeros.add(registrada.getNumero());
            hashes.add(registrada.getHashDocumento());

            if (numeros.contains(receita.getNumero())) {
                indicios.add(IndicioFraude.NUMERO_DUPLICADO);
            }

            if (receita.getHashDocumento() != null
                    && !receita.getHashDocumento().isBlank()
                    && hashes.contains(receita.getHashDocumento())) {
                indicios.add(IndicioFraude.HASH_DOCUMENTO_REPETIDO);
            }
        }

        if (receita.getDataEmissao().isAfter(dataReferencia)) {
            indicios.add(IndicioFraude.DATA_FUTURA);
        }

        if (receita.isUtilizada()) {
            indicios.add(IndicioFraude.RECEITA_REUTILIZADA);
        }

        if (!receita.isAssinaturaValida()) {
            indicios.add(IndicioFraude.ASSINATURA_INVALIDA);
        }

        return indicios.stream()
                .distinct()
                .toList();
    }
    
}