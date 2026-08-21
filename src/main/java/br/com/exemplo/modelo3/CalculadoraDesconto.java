package br.com.exemplo.modelo3;

public class CalculadoraDesconto {
    public double calcular(double preco, int quantidade) {
        if (preco < 0) throw new IllegalArgumentException("Preço inválido");
        
        // Regra de negócio simples: acima de 10 unidades, 10% de desconto
        if (quantidade >= 10) {
            return preco * 0.9;
        }
        return preco;
    }
}