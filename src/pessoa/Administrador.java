package src.pessoa;

import java.io.FileNotFoundException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class Administrador extends Pessoa {

	public Administrador(String id, String senha, String nome) throws VazioException {
		super(id, senha, nome);
	}

	public abstract void cadastrar() throws FileNotFoundException, IOException; /*{
		// Recebe o que se esta cadastrando
		int opcao = opcaoDeCadastro();

		String arquivo;
		switch(opcao) {
			case 1:
				System.out.println("Cadastro de Paciente");
				arquivo = "db/listaPaciente.txt";
				break;
			case 2:
				System.out.println("Cadastro de Tecnico de Enfermagem:");
				arquivo = "db/listaTecEnfermagem.txt";
				break;
			case 3:
				System.out.println("Cadastro de Medico:");
				arquivo = "db/listaMedico.txt";
				break;
			case 4:
				System.out.println("Cadastro de Atendente:");
				arquivo = "db/listaAtendente.txt";
				break;
			case 5:
				System.out.println("Cadastro de Gerente:");
				arquivo = "db/listaGerente.txt";
				break;
		}

	}

	private abstract int opcaoDeCadastro();
	*/


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

