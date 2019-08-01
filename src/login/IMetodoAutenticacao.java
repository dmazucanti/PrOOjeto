package login;
import java.io.FileNotFoundException;

public interface IMetodoAutenticacao {

	UsuarioAutenticado autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException;
	
	
}
