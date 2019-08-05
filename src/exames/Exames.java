package exames;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exames {
	private BufferedWriter br;
	private static Exames exames;
	
	private Exames() throws IOException {
		br = new BufferedWriter(new FileWriter("exames.txt"));
		br.write("LISTA DE EXAMES");
		br.newLine();
		br.flush();
	}
	
	//metodo getInstance para garantir padrao singleton
	public static Exames getInstance() throws IOException {
		if(exames == null) {
			exames = new Exames();
		}
		return exames;
	}
	
	//registro de novos exames na lista
	public void registraExame(String nomeExame) throws FileNotFoundException {
		try {			
			Scanner scan = new Scanner(new File("exames.txt"));
			while(scan.hasNext()){
				String line = scan.nextLine().toString();
				//caso o exame ja esteja registrado, retorna exceÃƒÂ§ÃƒÂ£o
				if(line.contains(nomeExame)){
					throw new Exception("Exame ja registrado");
				}
			}
			scan.close();
			//caso o exame seja novo, escreve no arquivo de exames
			br.write(nomeExame);
			br.newLine();
			br.flush();
			System.out.println("Exame registrado com sucesso!");
			
		} catch (Exception e) {
			System.out.println("Erro ao registrar exame: " + e);
		}
	}
	
	public void visualizarExames() throws FileNotFoundException{
		Scanner scanV = new Scanner(new File("exames.txt"));
		//imprime o cabecalho do arquivo
		String line = scanV.nextLine().toString();
		System.out.println(line);
		
		//imprime cada linha contendo os nomes dos exames
		while(scanV.hasNext()){
			line = scanV.nextLine().toString();
			System.out.println(line);
		}
		scanV.close();
	}
}
