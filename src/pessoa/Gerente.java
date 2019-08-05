package pessoa;

import agenda.Agenda;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gerente extends Administrador {
	
	public Gerente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	@Override
	public void menu () {
		int opcao = 0;
		while(opcao != 3) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("O que você quer fazer?\n1 - Visualizar agenda\n2 - Cadastrar um novo atendente\n3 - Sair da conta"));
			
			switch (opcao) {
				case 1 :
					visualizarAgenda();
					break;
				case 2 :
					try {
						cadastrar();
					} catch (FileNotFoundException e) {
						e.getMessage();
					} catch (IOException e) {
						e.getMessage();
					}
					break;
			}	
		}
	}
	
	@Override
	public void visualizarAgenda() {
		Agenda agenda = null;
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			agenda.visualizaAgenda();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected int opcaoDeCadastro() {

		if ((JOptionPane.showInputDialog("Digite: [C] para cancelar e qualquer outra tecla para continuar")).charAt(0) == 'C') return -1;
		return 4;
	}

	@Override
	protected String[] pegaInformacoes(int opcao) {
		// Pegando informacoes / interagindo com o usuario
		
		String nome = JOptionPane.showInputDialog("Cadastro de Atendente:\nInsira o nome: ");
		String senha = JOptionPane.showInputDialog("Cadastro de Atendente:\nInsira uma senha: ");
		
		String info = "";
		info = info.concat(senha).concat(" "+nome);

		String vetorInfo[] = info.split(" ");

		return vetorInfo;
	}

	@Override
	protected String tipoDeArquivo(int opcao) {
		return "listaAtendente.txt";
	}

	@Override
	protected void notificaSucesso(int opcao) {
		System.out.println("Atendente cadastrado com sucesso!");
	}

}


/* Referencias
https://stackoverflow.com/questions/17509781/how-to-read-last-line-in-a-text-file-using-java
https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
https://www.tutorialspoint.com/java/java_strings.htm
*/
