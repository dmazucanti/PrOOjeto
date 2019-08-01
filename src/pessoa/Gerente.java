package src.pessoa;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Gerente extends Pessoa /* implements Cadastrar */ {
	public Gerente(String nome, String id) throws VazioException {
		super(nome, id);
	}

	public void cadastrar() throws FileNotFoundException, IOException {
		Scanner scan = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new FileWriter("database/atendente.txt", true));

		System.out.println("Cadastro de Atendente:");
		System.out.print("Nome: "); String nome = scan.next();

		scan.close();

		String id = geraId("database/atendente.txt");

		// Tenta escrever um novo usuario
		try {
			bw.write(id + ", ");
			bw.write(nome + "\r\n");
			System.out.println("Atentente cadastrado com sucesso");
			System.out.println("Seu ID eh: " + id);
		} finally {
			bw.close();
		}
	}

	private String geraId(String arquivo) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(arquivo));

		// Busca a ultima linha
		String ultimaLinha = "";
		String linhaAtual = br.readLine();

		while (linhaAtual != null) {
			ultimaLinha = linhaAtual;
			linhaAtual = br.readLine();
		}
		br.close();


		// se fimDoId == -1, quer dizer que nao tem virgula no arquivo, ou seja, nao existem
		// atendentes registrados
		int fimDoId = ultimaLinha.indexOf(",");
		String idNovo;

		if (fimDoId != -1) {
			String idAntigo = ultimaLinha.substring(0, ultimaLinha.indexOf(",")).trim();

			int tmp = Integer.parseInt(idAntigo);
			tmp++;
			idNovo = String.valueOf(tmp);
		}
		else {
			idNovo = "3" + "001";
		}

		return idNovo;
	}

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
