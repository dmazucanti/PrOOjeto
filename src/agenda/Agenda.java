package agenda;
	
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Agenda {

	private BufferedReader buff;
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
            String[] args = line.split(", ");
            System.out.println("[" + args[0] + "] [" + args[1] +"] >> " + args[2] + ", Profissional: " + args[3] + ", Paciente: " + args[4]);
        }
		scan.close();
	}
	
	public void visualizaAgenda(String id) throws FileNotFoundException {
			
		Scanner scan = new Scanner(new File("agenda.txt"));
	
		while(scan.hasNext()){
			String line = scan.nextLine().toString();
			if (line.contains(id)) {
				String[] args = line.split(", ");
	            System.out.println("[" + args[0] + "] [" + args[1] +"] >> " + args[2] + ", Profissional: " + args[3] + ", Paciente: " + args[4]);
			}
		}
		scan.close();
	}
	
	public String[] leDados() throws FileNotFoundException, IOException{
		String[] agenda = new String[1000]; 
		String linha;
		int quant = 0; 

		buff = new BufferedReader(new FileReader("agenda.txt"));
		try {
			linha = buff.readLine();
			while (linha != null && quant < agenda.length-1) {
				agenda[quant] = linha;
				quant++;
				linha = buff.readLine();
			}
			System.out.println("O arquivo foi lido e carregado para a memoria!");
		} finally {
			buff.close();
		}
		return agenda;
	}
	
	public void desmarcaAgenda(String id, String data, String horario) throws IOException{
		
		Scanner scan = new Scanner(new File("agenda.txt"));
		String linha = null;
		
		while(scan.hasNext()){
			String line = scan.nextLine().toString();
			if (line.contains(id) && line.contains(data) && line.contains(horario)) {
				linha = line;
			}
		}
		scan.close();
		
		String[] agenda = leDados();
		int i = 0;
		while (agenda[i] != linha) {
			i++;
		}
		agenda[i] = null;
		
		while(i<agenda.length-1) {
			agenda[i] = agenda[i+1];
			i++;
		}
		
		File file = new File("agenda.txt");
		file.delete();
		
		i=0;
		while (agenda[i]!=null) {
			String[] args = agenda[i].split(", ");
			marcaAgenda(args[0], args[1], args[2],args[3], args[4]);
			i++;
		}
	}
	
	public void marcaAgenda(String data, String horario, String tipo, String idFunc, String idPac) throws FileNotFoundException{
		try {
			String novaLinha = data + ", "+horario+", " + tipo + ", " + idFunc + ", " + idPac;
			
			Scanner scan = new Scanner(new File("agenda.txt"));
			while(scan.hasNext()){
				String line = scan.nextLine().toString();
				if(line.contains(novaLinha)){
					throw new Exception("Este profissional não está disponível nesta data e horário");
				}
			}
	        
			//scan.close();
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