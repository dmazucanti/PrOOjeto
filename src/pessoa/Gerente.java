package pessoa;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Gerente extends Administrador {
	
	public Gerente(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	@Override
	public void cadastrar() throws FileNotFoundException, IOException {

		// Pegando informacoes / interagindo com o usuario
		Scanner scan = new Scanner(System.in);
		System.out.println("Cadastro de Atendente:");
		System.out.print("Insira o nome: "); String nome = scan.next();
		System.out.print("Insira uma senha: "); String senha = scan.next();
		// System.out.print("Confirme a senha: "); String senhaConfirmacao = scan.next();

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

	@Override
	public void visualizarAgenda() {

		System.out.println("Agenda!");
	}

}

//String id = ultimaLinha.charAt(0);

/* Referencias
https://stackoverflow.com/questions/17509781/how-to-read-last-line-in-a-text-file-using-java
https://stackoverflow.com/questions/5585779/how-do-i-convert-a-string-to-an-int-in-java
https://www.tutorialspoint.com/java/java_strings.htm
*/
