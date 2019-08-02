package pessoa;

public class TecEnfermagem extends Funcionario {

	private String[] tiposExames;

	public TecEnfermagem(String id, String senha, String nome, String... args) throws VazioException {
		super(id, senha, nome);
		tiposExames = args;
	}

	@Override
	public void visualizarAgenda() {
		//System.out.println("Agenda!");
	}
}
