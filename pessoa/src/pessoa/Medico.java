package pessoa;
public class Medico extends Funcionario{

	private String especialidade;

	public Medico(String esp, String nome){
		super(nome);
		especialidade=esp;
	}

	public void setEspecialidade(String esp){
		especialidade=esp;
	}

	public String getEspecialidade(){
		return especialidade;
	}

	public void VisualizarAgenda(){
		System.out.println("Façam-me");
	}
}
