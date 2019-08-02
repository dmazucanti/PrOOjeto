package pessoa;

public class TecEnfermagem extends Funcionario {

	private String[] tiposExames;

	public TecEnfermagem(String id, String senha, String nome, String... args) {
		super(id, senha, nome);
		tiposExames = args;
	}

	public void visualizarAgenda() {
		//System.out.println("Agenda!");
	}
}

