package pessoa;

public abstract class Funcionario extends Pessoa {
	
	public Funcionario(String nome) {
		super(nome);
	}

	public abstract void visualizarAgenda();
}
