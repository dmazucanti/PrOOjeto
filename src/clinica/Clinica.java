package clinica;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import agenda.Agenda;
import login.*;
import pessoa.*;

public class Clinica {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		Pessoa usuario = null;
		Agenda agenda = null;
		char log_in = '0';
		
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Digite qualquer tecla para fazer login. Para sair digite s: ");
		log_in = scan.next().charAt(0);
		while (log_in != 's') {
			try {
				usuario = VerificaSenha.autenticar();
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (UsuarioNaoAutenticadoException e) {
				usuario = null;
				System.out.println(e.getMessage());
			} catch (VazioException e) {
				System.out.println(e.getMessage());
			}
			
			
			if (usuario!=null)
				usuario.menu();
			
			System.out.println("Digite qualquer tecla para fazer login. Para sair digite s: ");
			log_in = scan.next().charAt(0);
		}
		scan.close();
		System.out.println("Fim!");
	}
}
