package src.pessoa;

public abstract class Funcionario extends Pessoa throws VazioException {
	
	public Funcionario(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

}
