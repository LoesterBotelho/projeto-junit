package br.com.exemplo.contabil;

import java.util.Objects;

public class ContaContabil {
	private String codigo;
	private String nome;
	private TipoNaturezaConta natureza;
	private boolean analitica; // true = analitica (aceita lancamentos), false = sintetica
	private boolean ativa;

	public ContaContabil(String codigo, String nome, TipoNaturezaConta natureza, boolean analitica) {
		this.codigo = Objects.requireNonNull(codigo, "Codigo nao pode ser nulo");
		this.nome = Objects.requireNonNull(nome, "Nome nao pode ser nulo");
		this.natureza = Objects.requireNonNull(natureza, "Natureza nao pode ser nula");
		this.analitica = analitica;
		this.ativa = true;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public TipoNaturezaConta getNatureza() {
		return natureza;
	}

	public boolean isAnalitica() {
		return analitica;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void inativar() {
		this.ativa = false;
	}

	public void ativar() {
		this.ativa = true;
	}

	@Override
	public String toString() {
		return "ContaContabil [codigo=" + codigo + 
				", nome=" + nome + 
				", natureza=" + natureza + 
				", analitica=" + analitica + 
				", ativa=" + ativa + 
				"]";
	}
	
	
}