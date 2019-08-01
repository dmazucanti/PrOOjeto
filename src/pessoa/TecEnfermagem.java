package pessoa;

public class TecEnfermagem extends Funcionario {

	private String[] tiposExames;

	public TecEnfermagem(String nome, String... args) {
		super(nome);
		tiposExames = args;
	}

	public void visualizarAgenda() {
		System.out.println("Agenda!");
	}
}

