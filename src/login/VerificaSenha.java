package login;

import pessoa.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;

public class VerificaSenha{

	private static String procuraAcesso(String arquivo, String id) throws FileNotFoundException{
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

	public static Pessoa autenticar() throws UsuarioNaoAutenticadoException, FileNotFoundException, VazioException {

		String id = JOptionPane.showInputDialog("ID:");
		String senha = JOptionPane.showInputDialog("Senha:");
		int key = criptog.hash(senha);//calcula hash da entrada
		Scanner instancia;
		String retornoSenha = null;
		Pessoa user = null;
		
		switch(id.charAt(0)) {//checa o perfil de acesso baseado no primeiro digito do id e instancia o objeto apropriado
			case '1':
				retornoSenha = procuraAcesso("listaPaciente.txt", id);
				instancia = new Scanner(new File("listaPaciente.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.contains(id)){
						String[] args = registro.split(", ");
						user = new Paciente(args[0],args[1],args[2],args[3],args[4],Integer.parseInt(args[5]),Float.parseFloat(args[6]),Integer.parseInt(args[7]),args[8],args[9]);
					}
				}
				break;
			case '2':
				retornoSenha = procuraAcesso("listaTecEnfermagem.txt", id);
				instancia = new Scanner(new File("listaTecEnfermagem.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.contains(id)){
						String[] args = registro.split(", ");
						String[] argvar = Arrays.copyOfRange(args,3,args.length);
						user = new TecEnfermagem(args[0],args[1],args[2],argvar);
					}
				}
				break;
			case '3':
				retornoSenha = procuraAcesso("listaMedico.txt", id);
				instancia = new Scanner(new File("listaMedico.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.contains(id)){
						String[] args = registro.split(", ");
						user = new Medico(args[0],args[1],args[2],args[3]);
					}
				}
				break;
			case '4':
				retornoSenha = procuraAcesso("listaAtendente.txt", id);
				instancia = new Scanner(new File("listaAtendente.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.contains(id)){
						String[] args = registro.split(", ");
						user = new Atendente(args[0], args[1], args[2]);
					}
				}
				break;
			case '5':
				retornoSenha = procuraAcesso("listaGerente.txt", id);
				instancia = new Scanner(new File("listaGerente.txt"));
				while(instancia.hasNext()){
					String registro = instancia.nextLine().toString();
					if(registro.contains(id)){
						String[] args = registro.split(", ");
						user = new Gerente(args[0],args[1],args[2]);
					}
				}
				break;
		}
		int reg = Integer.parseInt(retornoSenha); //converte pra int o registro do hash de cadastra()
		if(key == reg){
			return user;
		}
		else {
			throw new UsuarioNaoAutenticadoException();
		}
	}
}
