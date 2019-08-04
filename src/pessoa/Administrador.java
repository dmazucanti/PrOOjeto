package pessoa;

import java.io.FileNotFoundException;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.util.Scanner;
import java.util.InputMismatchException;


public abstract class Administrador extends Pessoa {

	public Administrador(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	public void cadastrar() throws FileNotFoundException, IOException {
		boolean ok = false;
		do {
			// Escolhe o que se quer cadastrar
			int opcao = opcaoDeCadastro();
			// Checa se o usuario cancelou o cadastro
			if (opcao == -1) {
				if(confirmaCancelamento()) return;
				else continue;
			}
			// Recebe as informacoes referentes a cada tipo de pessoa
			String info[] = pegaInformacoes(opcao);
			// Decide qual o arquivo sera usado
			String arquivo = tipoDeArquivo(opcao);
			// Tenta escrever no arquivo
			escreveInformacoes(arquivo, info);
			// Informa o sucesso
			notificaSucesso(opcao);
			ok = true;
		} while(!ok);
	}

	protected abstract int opcaoDeCadastro();
	private boolean confirmaCancelamento() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Cancelar?: [S] Cancela, [Outro] continua");
		if(scan.next().charAt(0) == 'S') return true;
		return false;
	}
	protected abstract String[] pegaInformacoes(int opcao);
	protected abstract String tipoDeArquivo(int opcao);
	private void escreveInformacoes(String arquivo, String[] info) throws FileNotFoundException, IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
		String id = geraId(arquivo);
		try {
			bw.write(id + ", ");
			for(int i=0; i < info.length-1; i++) bw.write(info[i] + ", "); 
				bw.write(info[info.length-1] + "\r\n");
				System.out.println("O ID gerado eh: " + id);
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
			idNovo = ultimaLinha.charAt(0) + "0001";
		}

		return idNovo;
	}
	protected abstract void notificaSucesso(int opcao);

}

