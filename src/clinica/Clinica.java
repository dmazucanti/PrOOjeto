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
		int log_in = 0;
		
		try { 
			agenda = Agenda.getInstance();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Para fazer login digite 1, para sair digite 0:");
		log_in = scan.nextInt();
		while (log_in == 1) {
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
			
			if (usuario instanceof Medico) {
				try {
					agenda.visualizaAgenda();
				} catch (FileNotFoundException e ){
					System.out.println(e.getMessage());
				}
			} 
			else if (usuario instanceof Paciente) {
				try {
					System.out.println("Id = " + usuario.getId());
					agenda.visualizaAgenda(usuario.getId());
				} catch (FileNotFoundException e ){
					System.out.println(e.getMessage());
				}
			}
			else if (usuario instanceof Gerente) {
				int opcao;
				System.out.println("O que você quer fazer?");
				System.out.println("1 - Visualizar agenda");
				System.out.println("2 - Cadastrar novo atendente");
				opcao = scan.nextInt();
				switch (opcao) {
					case 1 :
						try {
							agenda.visualizaAgenda();
						} catch (FileNotFoundException e ) {
							System.out.println(e.getMessage());
						}
						break;
					case 2 :
						try {
							((Gerente) usuario).cadastrar();
						} catch (FileNotFoundException e) {
							System.out.println(e.getMessage());
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
						break;
				}
			} else if (usuario instanceof Atendente){
				int opcao;
				System.out.println("O que você quer fazer?");
				System.out.println("1 - Visualizar agenda");
				System.out.println("2 - Cadastrar novo usuário");
				System.out.println("");
				opcao = scan.nextInt();
				switch (opcao) {
					case 1 :
						try {
							agenda.visualizaAgenda();
						} catch (FileNotFoundException e ) {
							System.out.println(e.getMessage());
						}
						break;
					case 2 :
						try {
							((Gerente) usuario).cadastrar();
						} catch (FileNotFoundException e) {
							System.out.println(e.getMessage());
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
						break;
				}
			}
			System.out.println("Para fazer login digite 1, para sair digite 0:");
			log_in = scan.nextInt();
		}
		scan.close();
		System.out.println("Fim!");
	}
}
