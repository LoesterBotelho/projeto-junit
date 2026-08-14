package br.com.exemplo.ecommerce;

public class Pedido {
	public enum Status {
		CRIADO, PAGO, ENVIADO, CANCELADO
	}

	private String id;
	private CarrinhoDeCompras carrinho;
	private Status status;

	public Pedido(String id, CarrinhoDeCompras carrinho) {
		this.id = id;
		this.carrinho = carrinho;
		this.status = Status.CRIADO;
	}

	public void pagar() {
		if (this.status != Status.CRIADO) {
			throw new IllegalStateException("Apenas pedidos criados podem ser pagos.");
		}
		this.status = Status.PAGO;
	}

	public void enviar() {
		if (this.status != Status.PAGO) {
			throw new IllegalStateException("O pedido precisa estar pago para ser enviado.");
		}
		this.status = Status.ENVIADO;
	}

	public Status getStatus() {
		return status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(CarrinhoDeCompras carrinho) {
		this.carrinho = carrinho;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}