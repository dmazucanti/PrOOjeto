package pessoa;

public class Medico extends Funcionario
{
	private String especialidade;

	public Medico(String id, String senha, String nome, String esp) {
		super(id, senha, nome);
		setEspecialidade(esp);
	}

	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String e) {
		especialidade = e;
	}

	public void visualizarAgenda() {
		System.out.println("Agenda");
	}
}

