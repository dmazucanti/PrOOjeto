package pessoa;

import agenda.Agenda;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gerente extends Administrador {
	
	public Gerente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	@Override
	public void menu () {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("O que voce quer fazer?");
		System.out.println("1 - Visualizar agenda");
		System.out.println("2 - Cadastrar um novo atendente");
		
		int opcao = scan.nextInt();
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
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite: [C] para cancelar e qualquer outra tecla para continuar");
		if (scan.next().charAt(0) == 'C') return -1;
		return 4;
	}

	@Override
	protected String[] pegaInformacoes(int opcao) {
		// Pegando informacoes / interagindo com o usuario
		Scanner scan = new Scanner(System.in);
		System.out.println("Cadastro de Atendente:");
		System.out.print("Insira o nome: "); String nome = scan.next();
		System.out.print("Insira uma senha: "); String senha = scan.next();

		String info = "";
		info = info.concat(senha).concat(" "+nome);

		String vetorInfo[] = info.split(" ");

		return vetorInfo;
	}

	@Override
	protected String tipoDeArquivo(int opcao) {
		return "../db/listaAtendente.txt";
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
