package pessoa;

import agenda.Agenda;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TecEnfermagem extends Pessoa {

	private String[] tiposExames;

	public TecEnfermagem(String id, String senha, String nome, String... args) throws VazioException {
		super(id, senha, nome);
		tiposExames = args;
	}

	@Override
	public void menu () {
		
		int opcao = 0;
		while(opcao != 2) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("O que voce quer fazer?\n1 - Visualizar agenda\n2 - Sair da conta"));

			switch (opcao) {
				case 1 :
					visualizarAgenda();
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
			agenda.visualizaAgenda(getId());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
