package br.com.exemplo.modelo3;

import java.time.LocalDate;

public class ValidadorCupom {

    public boolean validarCupom(String codigo, double valorCompra, LocalDate dataAtual) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("Código do cupom não pode ser vazio");
        }

        // Regra 1: Cupom BLACKFRIDAY dá 20% mas exige compra mínima de R$ 200,00
        if (codigo.equalsIgnoreCase("BLACKFRIDAY")) {
            if (valorCompra < 200.0) {
                throw new IllegalStateException("Compra mínima para este cupom é de R$ 200,00");
            }
            return true;
        }

        // Regra 2: Cupom de Natal expira após 25 de dezembro do ano corrente
        if (codigo.equalsIgnoreCase("NATAL10")) {
            LocalDate limite = LocalDate.of(dataAtual.getYear(), 12, 25);
            if (dataAtual.isAfter(limite)) {
                throw new IllegalStateException("Cupom expirado");
            }
            return true;
        }

        throw new IllegalArgumentException("Cupom inválido ou inexistente");
    }
}