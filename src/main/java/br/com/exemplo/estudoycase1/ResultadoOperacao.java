package br.com.exemplo.estudoycase1;

public record ResultadoOperacao<T>(
        boolean sucesso,
        String mensagem,
        T dados) {
	
}