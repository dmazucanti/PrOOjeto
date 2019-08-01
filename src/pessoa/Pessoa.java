package src.pessoa;

public abstract class Pessoa extends VazioException {

	private String nome;

	public Pessoa(String nome, String id) throws VazioException {
		setNome(nome);
		setId(id);
	}

	public void setNome(String nome) throws VazioException {
		if(!nome.isEmpty() && nome != null) 
			this.nome = nome;
		else
			throw new NomeVazioException(); 
	}

	public String getNome() {
		return nome;
	}

	public void setId (String id) {
		this.id = id;
	}

	public String getId() {	
		return id;
	}

	public abstract void visualizarAgenda();
}
