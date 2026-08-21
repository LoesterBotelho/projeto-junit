package br.com.exemplo.modelo3;

public class ServicoFreteExternoImpl implements ServicoFreteExterno {

    @Override
    public double consultarTaxa(String estado) {
        // Simulação de regras reais de frete por estado
        return switch (estado.toUpperCase()) {
            case "SP" -> 15.00;
            case "RJ" -> 20.00;
            case "RS", "SC", "PR" -> 25.00;
            default -> 45.00; // Demais regiões
        };
    }
}