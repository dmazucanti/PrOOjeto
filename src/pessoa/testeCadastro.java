package pessoa;

import java.io.FileNotFoundException;
import java.io.IOException;

public class testeCadastro
{
	public static void main(String args[])
	{
		try {
			Gerente g = new Gerente("10001", "12345", "Sergio");
			g.cadastrar();
		} catch (FileNotFoundException e) {
			System.out.println("Erro: Arquivo nao encontrado! " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro durante a leitura do arquivo!" + e.getMessage());
		} catch (VazioException e){
			System.out.println("nome" + e.getMessage());
		}

	}
}

