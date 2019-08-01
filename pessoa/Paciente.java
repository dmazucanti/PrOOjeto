package pessoa;

public class Paciente extends Pessoa {
	private String nome;
	private String sobrenome;
	private String sexo;
	private int idade;
	private float altura;
	private int peso;
	private String telefone;
	private String email;
	private String ortopedista;
	private String fisiatra;

	public Paciente(String nome) {
		super(nome);
	}
	
	public String getNome() {
		return super.getNome();
	}
	public void setNome(String nome) {
		if (!nome.isEmpty() && nome!=null)
			super.setNome(nome);
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		if (!sobrenome.isEmpty() && sobrenome!=null)
			this.sobrenome = sobrenome;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		if (!sexo.isEmpty() && sexo!=null)
			this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public float getAltura() {
		return altura;
	}
	public void setAltura(float altura) {
		this.altura = altura;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		if (!telefone.isEmpty() && telefone!=null)
			this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (!email.isEmpty() && email!=null)
			this.email = email;
	}
	public String getOrtopedista() {
		return ortopedista;
	}
	public void setOrtopedista(String ortopedista) {
		if (!ortopedista.isEmpty() && ortopedista!=null && ortopeditaExiste)
			//alteração de teste
			this.ortopedista = ortopedista;
	}
	public String getFisiatra() {
		return fisiatra;
	}
	public void setFisiatra(String fisiatra) {
		if (!fisiatra.isEmpty() && fisiatra!=null && fisiatraExiste)
			this.fisiatra = fisiatra;
	}
	
	
}
