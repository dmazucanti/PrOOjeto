package pessoa;

import agenda.Agenda;
import exames.Exames;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Atendente extends Administrador {
	
	public Atendente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}
	
	@Override
	public void menu () {
		
		Agenda agenda = null;
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		Exames exames = null;
		try { 
			exames = Exames.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		int opcao = 0;
		while(opcao != 7) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("O que você quer fazer?\n1 - Visualizar agenda\n"
					+ "2 - Cadastrar um novo usuario\n3 - Marcar um horario na agenda\n4 - Desmarcar um horario da agenda\n"
					+ "5 - Visualizar lista de exames\n6 - Registrar novo exame na lista\n7 - Sair da conta"));

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
					String tipo = JOptionPane.showInputDialog("O que você quer marcar?");
					String data = JOptionPane.showInputDialog("Data");
					String hora = JOptionPane.showInputDialog("Horário");
					String idFunc = JOptionPane.showInputDialog("ID funcionário");
					String idPac = JOptionPane.showInputDialog("ID paciente");
					try {
						agenda.marcaAgenda(data, hora, tipo, idFunc, idPac);
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4 :
					String id = JOptionPane.showInputDialog("Digite o ID de quem pediu para desmarcar");
					String datad = JOptionPane.showInputDialog("Data");
					String horario = JOptionPane.showInputDialog("Horario");
					
					try {
						agenda.desmarcaAgenda(id, datad, horario);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5:
					try {
						exames.visualizarExames();
						break;
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
				case 6:
					String newExame = JOptionPane.showInputDialog("Digite o nome do exame:");
					try {
						exames.registraExame(newExame);
						break;
					} catch (FileNotFoundException e) {
						System.out.println(e.getMessage());
					}
			}
		}
	}
	
	@Override
	public void visualizarAgenda(){
		Agenda agenda = null;
		
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		int opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 para visualizar a agenda "
				+ "geral, outro numero para visualizar a agenda de um usuario especifico"));

		if(opcao == 1) {
			try {
				agenda.visualizaAgenda();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		} else {
			String id = JOptionPane.showInputDialog("Digite o ID da pessoa cuja agenda voce quer visualizar: ");

			try {
				agenda.visualizaAgenda(id);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
	}


	@Override
	protected int opcaoDeCadastro() {
		// Pegando informacoes / interagindo com o usuario

		String tmp = JOptionPane.showInputDialog("Escolha a opcao de cadastro:\n[1] Paciente\n"
				+ "[2] Tecnico de Enfermagem\n[3] Medico\n[Outro] Cancela Cadastro");

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
				return "listaPaciente.txt";
			case 2:
				return "listaTecEnfermagem.txt";
			default:
				return "listaMedico.txt";
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
