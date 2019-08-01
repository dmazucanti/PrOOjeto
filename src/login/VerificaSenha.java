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
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(id)){
            	return line.substring(line.lastIndexOf(',') + 1).trim();
            }
        }
		return null;
	}
	
	@Override
	public UsuarioAutenticado autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Id:");
	    String id = scanner.next(); 
			    
	    System.out.println("Tipo de Acesso: [1-Paciente] [2-Medico] [3-Tecnico de Enfermagem] [4-Atendente] [5-Gerente]");
	    int tipoAcesso = scanner.nextInt(); 
	    
		System.out.println("Senha:");
		String senha = scanner.next(); 
		
		String retornoSenha = null;
		String StringAcesso = null;
		switch(tipoAcesso) {
		case 1:
			retornoSenha = procuraAcesso("listaPaciente.txt", id);
			StringAcesso = "Paciente";
			break;
		case 2:
			retornoSenha = procuraAcesso("listaMedico.txt", id);
			StringAcesso = "Medico";
			break;
		case 3:
			retornoSenha = procuraAcesso("listaTexEnfermagem.txt", id);
			StringAcesso = "Tecnico de Enfermagem";
			break;
		case 4:
			retornoSenha = procuraAcesso("listaAtendente.txt", id);
			StringAcesso = "Atendente";
			break;
		case 5:
			retornoSenha = procuraAcesso("listaGerente.txt", id);
			StringAcesso = "Gerente";
			break;
		}
		
		scanner.close();
		if(senha.equals(retornoSenha)){
			return new UsuarioAutenticado(id, StringAcesso);
		}
		else {
			throw new UsuarioNaoAutenticadoException();
		}
	}
}
