package login;
import pessoa.*;
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
		    String[] pswd = line.split(",");
            	return pswd[1].trim();
	    }
        }
		return null;
	}
	
	@Override
	public Pessoa autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Id:");
	    String id = scanner.next(); 
			    
		System.out.println("Senha:");
		String senha = scanner.next(); 

		Scanner instancia;
		
		String retornoSenha = null;
		String StringAcesso = null;
		switch(id.charAt(0)) {

			case 1:
				retornoSenha = procuraAcesso("../../db/listaPaciente.txt", id);
				instancia = new Scanner(new File("../../db/listaPaciente.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.cointais(id)){
						String[] args = registro.split(", ");
					}
				}
				Paciente user = new Paciente(args[0],args[1],args[2],args[3],args[4],Integer.parseInt(args[5]),Float.parseFloat(args[6]),Integer.parseInt(args[7]),args[8],args[9]);
				break;
			case 2:
				retornoSenha = procuraAcesso("../../db/listaMedico.txt", id);
				instancia = new Scanner(new File("../../db/listaMedico.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.cointais(id)){
						String[] args = registro.split(", ");
					}
				}
				Medico user = new Medico(args[0],args[1],args[2],args[3]);
				break;
			case 3:
				retornoSenha = procuraAcesso("../../db/listaTecEnfermagem.txt", id);
				instancia = new Scanner(new File("../../db/listaTecEnfermagem.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.cointais(id)){
						String[] args = registro.split(", ");
					}
				}
				String[] argvar = Arrays.copyOfRange(args,3,args.length);
				TecEnfermagem user = new TecEnfermagem(args[0].args[1],args[2],argvar);
				break;
			case 4:
				retornoSenha = procuraAcesso("../../db/listaAtendente.txt", id);
				instancia = new Scanner(new File("../../db/listaAtendente.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.cointais(id)){
						String[] args = registro.split(", ");
					}
				}
				Atendente user = new Atendente();
				break;
			case 5:
				retornoSenha = procuraAcesso("../../db/listaGerente.txt", id);
				instancia = new Scanner(new File("../../db/listaGerente.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.cointais(id)){
						String[] args = registro.split(", ");
					}
				}
				Gerente user = new Gerente(args[0],args[1],args[2]);
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
