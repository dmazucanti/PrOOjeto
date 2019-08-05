package pessoa;

import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import login.criptog;


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

	// Recebe do usuario o que ele deseja cadastrar
	// e oferece a opcao de cancelar o cadastro
	protected abstract int opcaoDeCadastro();

	// Confirma o cancelamento caso seja requisitado
	private boolean confirmaCancelamento() {
		
		if ((JOptionPane.showInputDialog("Cancelar?\n[S] Cancela\n[Outro] continua")).charAt(0) == 'S') return true;
		return false;
	}

	// Recebe as informacoes do usuario dependendo do tipo de pessoa que sera cadastrada
	protected abstract String[] pegaInformacoes(int opcao);
	// Devolve o caminho ate o arquivo referente a cada tipo de pessoa
	protected abstract String tipoDeArquivo(int opcao);

	// Escreve as informacoes do cadastrado recebidas por um vetor; em um arquivo da database
	private void escreveInformacoes(String arquivo, String[] info) throws FileNotFoundException, IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true));
		String id = geraId(arquivo);
		try {
			bw.write(id + ", ");
			int hash = criptog.hash(info[0]); //calcula o hash da senha
			bw.write(hash + ", ");
			for(int i=1; i < info.length-1; i++) bw.write(info[i] + ", "); 
				bw.write(info[info.length-1] + "\r\n");
				System.out.println("O ID gerado eh: " + id);
		} finally {
			bw.close();
		}
	}

	// Gera o proximo ID de um determinado tipo de pessoa
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
			// Consegue o ultimo ID salvo no arquivo
			String idAntigo = ultimaLinha.substring(0, ultimaLinha.indexOf(",")).trim();

			// Incrementa o ID para gerar um novo
			int tmp = Integer.parseInt(idAntigo);
			tmp++;
			idNovo = String.valueOf(tmp);
		}
		// Caso nao haja nenhum usuario cadastrado ainda, pega o identificador daquele tipo
		// de usuario e cria o primeiro ID
		else {
			idNovo = ultimaLinha.charAt(0) + "0001";
		}
		return idNovo;
	}

	// Imprime uma mensagem personalizada de sucesso para cada tipo de cadastro
	protected abstract void notificaSucesso(int opcao);

}


// Isso podia ser uma interface, mas haveria o problema de precisarmos do Java 8 para que houvesse implementacao
