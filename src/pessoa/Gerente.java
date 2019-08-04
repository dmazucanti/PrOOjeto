package pessoa;

import agenda.Agenda;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Gerente extends Administrador {
	
	public Gerente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	@Override
	public void menu () {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("O que você quer fazer?");
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
	public void cadastrar() throws FileNotFoundException, IOException {

		// Pegando informacoes / interagindo com o usuario
		Scanner scan = new Scanner(System.in);
		System.out.println("Cadastro de Atendente:");
		System.out.print("Insira o nome: "); String nome = scan.next();
		System.out.print("Insira uma senha: "); String senha = scan.next();

		String arquivo = "../db/listaAtendente.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
		String id = super.geraId(arquivo);

		// Tenta escrever um novo usuario
		try {
			bw.write(id + ", ");
			bw.write(senha + ", ");
			bw.write(nome + "\r\n");
			System.out.println("Atendente cadastrado com sucesso");
			System.out.println("O ID gerado eh: " + id);
		} finally {
			bw.close();
		}
	}
}