package br.com.exemplo.estudoxcase1;

public class PagamentoRecusadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PagamentoRecusadoException(String mensagem) {
        super(mensagem);
    }
	
}