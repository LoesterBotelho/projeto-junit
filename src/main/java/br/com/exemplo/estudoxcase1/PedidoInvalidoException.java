package br.com.exemplo.estudoxcase1;

public class PedidoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoInvalidoException(String mensagem) {
        super(mensagem);
    }
	
}