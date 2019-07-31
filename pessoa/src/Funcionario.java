
public abstract class Funcionario extends Pessoa{

	private String nome;
	
	public Funcionario(){
		super();
	}

	@Override
	public void setNome(String nome){
		super.getNome(nome);
	}
	
	@Override
	public String getNome(){
		return super.setNome();
	}

	public abstract void visualizarAgenda();
}
