package agenda;
	
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Agenda {

	private BufferedWriter br;
	private static Agenda agenda;

	private Agenda() throws IOException {
		br = new BufferedWriter(new FileWriter("agenda.txt"));
		br.write("AGENDA 2019");
		br.newLine();
		br.flush();
	}

	public static Agenda getInstance() throws IOException {
		if(agenda == null) {
			agenda = new Agenda();
		}
		return agenda;
	}
	public void visualizaAgenda() throws FileNotFoundException {

		Scanner scan = new Scanner(new File("agenda.txt"));

		while(scan.hasNext()){
			String line = scan.nextLine().toString();
			System.out.println(line);
		}
		scan.close();
	}
	
	public void visualizaAgenda(String nome) throws FileNotFoundException {
			
			Scanner scan = new Scanner(new File("agenda.txt"));
	
			while(scan.hasNext()){
				String line = scan.nextLine().toString();
				System.out.println(line);
			}
			scan.close();
		}
	public void desmarcaAgenda(String nomePac, String data, String horario) throws FileNotFoundException{
		
	}
	public void marcaAgenda(String tipo, String nome, String nomePac, String data, String horario) throws FileNotFoundException{
		try {
			String novaLinha = "[" +data+ "] ["+horario+"] >> " + tipo + ", Profissional: " + nome + ", Paciente: " + nomePac;
			
			Scanner scan = new Scanner(new File("agenda.txt"));
			while(scan.hasNext()){
				String line = scan.nextLine().toString();
				if(line.contains(novaLinha)){
					throw new Exception("Este profissional não está disponível nesta data e horário");
				}
			}

			br.write(novaLinha);
			br.newLine();
			br.flush();

			System.out.println("Horário agendado com sucesso!");

		} catch (Exception e) {
			System.out.println("Erro ao marcar na agenda: " + e);
		}
	}
		
	public void fecharAgenda() throws IOException {
		br.close();
	}
}
