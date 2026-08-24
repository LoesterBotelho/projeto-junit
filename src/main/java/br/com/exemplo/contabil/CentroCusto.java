package br.com.exemplo.contabil;

import java.util.Objects;

public class CentroCusto {
	private String codigo;
	private String descricao;
	private boolean ativo;

	public CentroCusto(String codigo, String descricao) {
		this.codigo = Objects.requireNonNull(codigo, "Codigo nao pode ser nulo");
		this.descricao = Objects.requireNonNull(descricao, "Descricao nao pode ser nula");
		this.ativo = true;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void inativar() {
		this.ativo = false;
	}
}