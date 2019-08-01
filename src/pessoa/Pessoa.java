package src.pessoa;

public abstract class Pessoa extends NomeVazioException {

	private String nome;

	public Pessoa(String nome) throws NomeVazioException {
		setNome(nome);
	}

	public void setNome(String nome) throws NomeVazioException {
		if(!nome.isEmpty() && nome != null) 
			this.nome = nome;
		else
			throw new NomeVazioException(); 
	}

	public String getNome() {
		return nome;
	}

	public abstract void visualizarAgenda();
}
