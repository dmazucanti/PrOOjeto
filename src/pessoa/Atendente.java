package pessoa;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Atendente extends Administrador {
	public Atendente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	@Override
	public void cadastrar() throws FileNotFoundException, IOException {
		// Pegando informacoes / interagindo com o usuario
		Scanner scan = new Scanner(System.in);

		System.out.println("Escolha a opcao de cadastro:");
		System.out.println("[1] Paciente, [2] Tecnico de Enfermagem, [3] Medico");
		// tratamenteo de erro e/ou do while
		int opcao = scan.nextInt();

		String arquivo;
		switch(opcao) {
			case 1:
				System.out.println("Cadastro de Paciente");
				cadastraPaciente();
				break;
			case 2:
				System.out.println("Cadastro de Tecnico de Enfermagem:");
				cadastraTecEnfermagem();
				break;
			case 3:
				System.out.println("Cadastro de Medico:");
				cadastraMedico();
				break;
		}
	}

	private void cadastraPaciente() throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Insira o nome: "); String nome = scan.nextLine();
		System.out.print("Insira o sobrenome: "); String sobrenome = scan.nextLine();
		System.out.print("Insira o sexo: "); String sexo = scan.nextLine();
		System.out.print("Insira a idade: "); String idade = scan.nextLine();
		System.out.print("Insira a altura: "); String altura = scan.nextLine();
		System.out.print("Insira o peso: "); String peso = scan.nextLine();
		System.out.print("Insira o telefone: "); String telefone = scan.nextLine();
		System.out.print("Insira o e-mail: "); String email = scan.nextLine();
		System.out.print("Insira o ortopedista: "); String ortopedista = scan.nextLine();
		System.out.print("Insira o fisiatra: "); String fisiatra = scan.nextLine();
		System.out.print("Insira uma senha: "); String senha = scan.nextLine();

		String arquivo = "../../db/listaPaciente.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
		String id = super.geraId(arquivo);

		// Tenta escrever um novo usuario
		try {
			bw.write(id + ", ");
			bw.write(senha + ", ");
			bw.write(nome + ", ");
			bw.write(sobrenome + ", ");
			bw.write(sexo + ", ");
			bw.write(idade + ", ");
			bw.write(altura + ", ");
			bw.write(peso + ", ");
			bw.write(telefone + ", ");
			bw.write(email + ", ");
			bw.write(ortopedista + ", ");
			bw.write(fisiatra + ", ");
			System.out.println("Paciente cadastrado com sucesso");
			System.out.println("O ID gerado eh: " + id);
		} finally {
			bw.close();
		}
	}
	private void cadastraTecEnfermagem() throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Insira o nome: "); String nome = scan.nextLine();
		// Decidir formato
		System.out.print("Insira os tipos de exames: "); String stringExames = scan.nextLine();
		System.out.print("Insira uma senha: "); String senha = scan.nextLine();

		String exames[] = stringExames.split(" ");

		String arquivo = "../../db/listaTecEnfermagem.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
		String id = super.geraId(arquivo);

		// Tenta escrever um novo usuario
		try {
			bw.write(id + ", ");
			bw.write(senha + ", ");
			bw.write(nome + ", ");
			for(int i=0; i < exames.length-1; i++) bw.write(exames[i] + ", "); 
			bw.write(exames[exames.length-1] + "\r\n");
			System.out.println("Tecnico de enfermagem cadastrado com sucesso");
			System.out.println("O ID gerado eh: " + id);
		} finally {
			bw.close();
		}
	}
	private void cadastraMedico() throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("Insira o nome: "); String nome = scan.nextLine();
		System.out.print("Indique a especialidade: "); String especialidade = scan.nextLine();
		System.out.print("Insira uma senha: "); String senha = scan.nextLine();

		String arquivo = "../../db/listaMedico.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
		String id = super.geraId(arquivo);

		// Tenta escrever um novo usuario
		try {
			bw.write(id + ", ");
			bw.write(senha + ", ");
			bw.write(nome + ", ");
			bw.write(especialidade + "\r\n");
			System.out.println("Medico cadastrado com sucesso");
			System.out.println("ID gerado: " + id);
		} finally {
			bw.close();
		}
	}

	public void visualizarAgenda(){
		return;
	}

}

