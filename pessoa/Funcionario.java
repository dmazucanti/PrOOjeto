
public abstract class Funcionario extends Pessoa{

	private String nome;
	
	public Funcionario(){
		super();
	}

	@Override
	public void setNome(String nome){
		super.setNome(nome);
	}
	
	@Override
	public String getNome(){
		return super.getNome();
	}

	public abstract void VisualizarAgenda();
}
