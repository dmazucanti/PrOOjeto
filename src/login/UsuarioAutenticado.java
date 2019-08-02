package login;

public class UsuarioAutenticado {

	private String id;
	private String tipoAcesso;
	
	public UsuarioAutenticado(String id, String tipoAcesso) {
		this.id = id;
		this.tipoAcesso = tipoAcesso;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getTipoAcesso() {
		return this.tipoAcesso;
	}
	
}