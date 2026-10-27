package br.com.exemplo.estudoycase1;

public class TarefaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TarefaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
