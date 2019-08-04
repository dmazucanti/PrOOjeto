package pessoa;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import agenda.Agenda;

public class TecEnfermagem extends Pessoa {

	private String[] tiposExames;

	public TecEnfermagem(String id, String senha, String nome, String... args) throws VazioException {
		super(id, senha, nome);
		tiposExames = args;
	}

	@Override
	public void menu () {
		Scanner scan = new Scanner(System.in);
		
		int opcao;
		
		System.out.println("O que você quer fazer?");
		System.out.println("1 - Visualizar agenda");
		
		opcao = scan.nextInt();
		switch (opcao) {
			case 1 :
				visualizarAgenda();
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
			agenda.visualizaAgenda(getId());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
