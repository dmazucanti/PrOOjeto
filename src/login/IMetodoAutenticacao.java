package login;

import pessoa.*;
import java.io.FileNotFoundException;

public interface IMetodoAutenticacao {

	Pessoa autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException;
}

