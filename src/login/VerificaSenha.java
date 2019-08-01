package login;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class VerificaSenha implements IMetodoAutenticacao {

	public VerificaSenha() throws UsuarioNaoAutenticadoException, FileNotFoundException {
		autenticar();
	}

	private String procuraAcesso(String arquivo, String id) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(arquivo));
		while(scan.hasNext()) {
			String line = scan.nextLine().toString();
			if(line.contains(id)) {
				return line.substring(line.lastIndexOf(',') + 1).trim();
			}
		}
		return null;
	}

	@Override
	public Pessoa autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Id:");
		String id = scanner.next(); 
		// Ainda precisamos checar se o ID e valido

		System.out.println("Senha:");
		String senha = scanner.next(); 

		String retornoSenha = null;
		String StringAcesso = null;
		switch(id[0]) {
			case 1:
				retornoSenha = procuraAcesso("listaPaciente.txt", id);
				Paciente user = new Paciente();
				break;
			case 2:
				retornoSenha = procuraAcesso("listaMedico.txt", id);
				Medico user = new Medico();
				break;
			case 3:
				retornoSenha = procuraAcesso("listaTexEnfermagem.txt", id);
				TecEnfermagem user = new TecEnfermagem();
				break;
			case 4:
				retornoSenha = procuraAcesso("listaAtendente.txt", id);
				Atendente user = new Atendente();
				break;
			case 5:
				retornoSenha = procuraAcesso("listaGerente.txt", id);
				Gerente user = new Gerente();
				break;
		}
		
		scanner.close();
		if(senha.equals(retornoSenha)){
			return user;
		}
		else {
			throw new UsuarioNaoAutenticadoException();
		}
	}
}
