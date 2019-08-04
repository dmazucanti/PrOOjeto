package pessoa;

import agenda.Agenda;
import java.util.Scanner;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Atendente extends Administrador {
	public Atendente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}
	
	@Override
	public void menu () {
		Scanner scan = new Scanner(System.in);
		
		Agenda agenda = null;
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		int opcao;
		
		System.out.println("O que voce quer fazer?");
		System.out.println("1 - Visualizar agenda");
		System.out.println("2 - Cadastrar um novo usuario");
		System.out.println("3 - Marcar um horario na agenda");
		System.out.println("4 - Desmarcar um horario da agenda");
		
		opcao = scan.nextInt();
		switch (opcao) {
			case 1 :
				visualizarAgenda();
				break;
				
			case 2 :
				try {
					cadastrar();
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 3 :
				System.out.println("O que voce quer marcar? "); String tipo = scan.next();
				System.out.print("Data: "); String data = scan.next();
				System.out.print("Horario: "); String hora = scan.next();
				System.out.print("ID funcionario: "); String idFunc = scan.next();
				System.out.print("ID paciente: "); String idPac = scan.next();
				
				try {
					agenda.marcaAgenda(data, hora, tipo, idFunc, idPac);
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
				
			case 4 :
				System.out.println("Digite o id de quem esta desmarcando: "); String id = scan.next();
				System.out.println("Digite a data: "); String datad = scan.next();
				System.out.println("Digite o horario: "); String horario = scan.next();
				
				try {
					agenda.desmarcaAgenda(id, datad, horario);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
		}
	}
	
	@Override
	public void visualizarAgenda(){
		return;
	}


	@Override
	protected int opcaoDeCadastro() {
		// Pegando informacoes / interagindo com o usuario
		Scanner scan = new Scanner(System.in);

		System.out.println("Escolha a opcao de cadastro:");
		System.out.println("[1] Paciente, [2] Tecnico de Enfermagem, [3] Medico, [Outro] Cancela Cadastro");
		// Pode gerar uma InputMismatchException
		// int opcao = scan.nextInt();
		
		String tmp = scan.next();
		int opcao = 0;
		try {
			opcao = Integer.parseInt(tmp);
		} catch (Exception e) {
			return -1;
		}

		return (((opcao > 0) && (opcao < 4)) ? (opcao) : (-1));
	}
	@Override
	protected String[] pegaInformacoes(int opcao) {
		Scanner scan = new Scanner(System.in);

		String extrasProfissao = "";
		System.out.print("Insira o nome: "); String nome = scan.nextLine();
		switch (opcao) {
			case 1:
				System.out.print("Insira o sobrenome: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira o sexo: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira a idade: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira a altura: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira o peso: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira o telefone: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira o e-mail: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira o ortopedista: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine() + " ");
				System.out.print("Insira o fisiatra: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine());
				break;
			case 2:
				// Decidir formato
				System.out.print("Insira os tipos de exames: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine());
				break;
			case 3:
				System.out.print("Indique a especialidade: ");
				extrasProfissao = extrasProfissao.concat(scan.nextLine());
				break;
		}
		System.out.print("Insira uma senha: "); String senha = scan.nextLine();

		String info = "";
		info = info.concat(senha+" ").concat(nome+" ").concat(extrasProfissao);

		String vetorInfo[] = info.split(" ");

		return vetorInfo;
	}
	@Override
	protected String tipoDeArquivo(int opcao) {
		switch (opcao) {
			case 1:
				return "../db/listaPaciente.txt";
			case 2:
				return "../db/listaTecEnfermagem.txt";
			default:
				return "../db/listaMedico.txt";
		}
	}
	@Override
	protected void notificaSucesso(int opcao) {
		// Talvez pudessemos passar so a string do tipo de pessoa e concatenar com o resto
		// da frase
		switch (opcao) {
			case 1:
				System.out.println("Paciente cadastrado com sucesso!");
				break;
			case 2:
				System.out.println("Tecnico de Enfermagem cadastrado com sucesso!");
				break;
			default:
				System.out.println("Medico cadastrado com sucesso!");
				break;
		}
	}
}


// Talvez Strategy realmente fosse melhor :c
// Para adicionar um novo tipo de pessoa teriamos que mexer em todos esses metodos

// InputMismatchException
// https://stackoverflow.com/questions/14027537/why-am-i-getting-inputmismatchexception#14027583

// Porque nao fechar os Scanners
// https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input

// Como resolver
// https://stackoverflow.com/questions/25506240/how-to-close-a-scanner-without-closing-the-underlying-system-in
// https://stackoverflow.com/questions/14962082/close-scanner-without-closing-system-in
