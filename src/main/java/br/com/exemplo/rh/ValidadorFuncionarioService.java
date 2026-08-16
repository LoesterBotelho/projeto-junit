package br.com.exemplo.rh;

public class ValidadorFuncionarioService {
	public boolean validar(Funcionario funcionario) {
		if (funcionario == null)
			return false;
		if (funcionario.getNome() == null || funcionario.getNome().trim().isEmpty())
			return false;
		if (funcionario.getCpf() == null || funcionario.getCpf().length() != 11)
			return false;
		if (funcionario.getSalarioBase() <= 0)
			return false;
		return true;
	}
}