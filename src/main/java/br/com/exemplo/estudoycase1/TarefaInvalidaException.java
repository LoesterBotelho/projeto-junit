package br.com.exemplo.estudoycase1;

public class TarefaInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TarefaInvalidaException(String mensagem) {
        super(mensagem);
    }
}