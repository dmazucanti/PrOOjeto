package pessoa;

import agenda.Agenda;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Medico extends Pessoa {
	
	private String especialidade;

	public Medico(String id, String senha, String nome, String esp) throws VazioException {
		super(id, senha, nome);
		setEspecialidade(esp);
	}
	
	@Override
	public void menu () {
		
		int opcao = 0;
		while(opcao != 2) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("O que você quer fazer?\n1 - Visualizar agenda\n2 - Sair da conta"));

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
	
	public String getEspecialidade() {
		return especialidade;
	}
	
	public void setEspecialidade(String e) {
		especialidade = e;
	}
}
