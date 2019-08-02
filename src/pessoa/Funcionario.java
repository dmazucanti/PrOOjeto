package src.pessoa;

public abstract class Funcionario extends Pessoa throws VazioException {
	
	public Funcionario(String nome, String senha, String id) throws VazioException {
		super(nome, senha, id);
	}

}
