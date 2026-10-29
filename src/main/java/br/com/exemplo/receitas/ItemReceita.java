package br.com.exemplo.receitas;

import java.util.Objects;

public class ItemReceita {

    private final Medicamento medicamento;
    private final String dosagem;
    private final int quantidade;
    private final String instrucoesUso;

    public ItemReceita(
            Medicamento medicamento,
            String dosagem,
            int quantidade,
            String instrucoesUso) {

        this.medicamento = Objects.requireNonNull(medicamento);
        this.dosagem = Objects.requireNonNull(dosagem);
        this.instrucoesUso = Objects.requireNonNull(instrucoesUso);

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                    "A quantidade deve ser maior que zero.");
        }

        if (dosagem.isBlank()) {
            throw new IllegalArgumentException(
                    "A dosagem é obrigatória.");
        }

        if (instrucoesUso.isBlank()) {
            throw new IllegalArgumentException(
                    "As instruções de uso são obrigatórias.");
        }

        this.quantidade = quantidade;
    }

    public Medicamento getMedicamento() {
        return medicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getInstrucoesUso() {
        return instrucoesUso;
    }
    
}