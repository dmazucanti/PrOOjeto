package src.pessoa;

public abstract class Pessoa extends VazioException {

	private String id;
	private String senha;
	private String nome;

	public Pessoa(String id, String senha, String nome) throws VazioException {
		setNome(nome);
		setSenha(senha)
		setId(id);
	}

	public void setNome(String nome) throws VazioException {
		if(!nome.isEmpty() && nome != null) 
			this.nome = nome;
		else
			throw new VazioException(); 
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
	
	public void setSenha (String senha) {
		this.senha = senha;
	}
	
	public String getSenha () {
		return senha;
	}

	public abstract void visualizarAgenda();
}
