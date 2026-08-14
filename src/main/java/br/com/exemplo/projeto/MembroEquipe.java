package br.com.exemplo.projeto;

public class MembroEquipe {
	private String id;
	private String nome;
	private String email;

	public MembroEquipe(String id, String nome, String email) {
		if (email == null || !email.contains("@")) {
			throw new IllegalArgumentException("E-mail inválido.");
		}
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	@Override
	public String toString() {
		return "MembroEquipe [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}

}