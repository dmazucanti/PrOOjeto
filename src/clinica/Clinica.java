package clinica;

import login.*;
import pessoa.*;
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;

public class Clinica {

	public static void main(String[] args) {
		
		Pessoa usuario = null;
		
		char log_in = (JOptionPane.showInputDialog("Digite qualquer tecla para fazer login. Para sair digite s:")).charAt(0);

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
			
			log_in = (JOptionPane.showInputDialog("Digite qualquer tecla para fazer login. Para sair digite s:")).charAt(0);
		}
		System.out.println("Fim!");
	}
}