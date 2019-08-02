package src.pessoa;

import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Administrador extends Pessoa {

	public Administrador(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	// Acessavel por classes no mesmo pacote e pelas suas subclasses
	protected String geraId(String arquivo) throws FileNotFoundException, IOException {
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

}

