package pessoa;

public class Medico extends Funcionario
{
	private String especialidade;

	public Medico(String nome) {
		super(nome);
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

