package agenda;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Agenda {

	private BufferedReader buff;
	private BufferedWriter br;
	private static Agenda agenda;
	
	private Agenda() throws IOException {
		br = new BufferedWriter(new FileWriter("../db/agenda.txt"));
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
	
	private String recuperaNome(String idPac) throws FileNotFoundException{
		String nomePaciente = "";
		
		Scanner scanPac = new Scanner(new File("../db/listaPaciente.txt"));
		while(scanPac.hasNext()) {
			String linePac = scanPac.nextLine().toString();
			if(linePac.contains(idPac)) {
				String[] line_split = linePac.split(", ");
				nomePaciente = line_split[2] + " " + line_split[3];
    			break;
			}
		}
		return nomePaciente;
	}
	
	private String recuperaNome(String[] args) throws FileNotFoundException{
		String nomeProfissional = "";
		
	//Recupera nome do mÃ©dico por meio do id
        if(args[2].equals("consulta") || args[2].equals("Consulta")) {
        	Scanner scanMed = new Scanner(new File("../db/listaMedico.txt"));
        	while(scanMed.hasNext()) {
        		String lineMed = scanMed.nextLine().toString();
        		if(lineMed.contains(args[3])) {
        			String[] line_split = lineMed.split(", ");
        			nomeProfissional = line_split[2];
        			break;
        		}
        	}
        }
        else { 
		//Recupera nome do tecnico por meio do id
		Scanner scanTec = new Scanner(new File("../db/listaTecEnfermagem.txt"));
        	while(scanTec.hasNext()) {
        		String lineTec = scanTec.nextLine().toString();
        		if(lineTec.contains(args[3])) {
        			String[] line_split = lineTec.split(", ");
        			nomeProfissional = line_split[2];
        			break;
        		}
        	}
        }
        return nomeProfissional;
	}
	
	public void visualizaAgenda() throws FileNotFoundException {	
		Scanner scan = new Scanner(new File("../db/agenda.txt"));
		String line = scan.nextLine().toString();
		System.out.println(line);
		while(scan.hasNext()){
			line = scan.nextLine().toString();
			String[] args = line.split(", ");
			String nomeProfissional = recuperaNome(args);
			String nomePaciente = recuperaNome(args[4]);
			System.out.println("[" + args[0] + "] [" + args[1] +"] >> " + args[2] + ", Profissional: " + nomeProfissional + ", Paciente: " + nomePaciente);
		}
		scan.close();
	}
	
	public void visualizaAgenda(String id) throws FileNotFoundException {
			
		Scanner scan = new Scanner(new File("../db/agenda.txt"));
		String line = scan.nextLine().toString();
		System.out.println(line);
		while(scan.hasNext()){
			line = scan.nextLine().toString();
			if (line.contains(id)) {
				String[] args = line.split(", ");
				String nomeProfissional = recuperaNome(args);
				String nomePaciente = recuperaNome(args[4]);
				System.out.println("[" + args[0] + "] [" + args[1] +"] >> " + args[2] + ", Profissional: " + nomeProfissional + ", Paciente: " + nomePaciente);
			}
		}
		scan.close();
	}
	
	public String[] leDados() throws FileNotFoundException, IOException{
		String[] agenda = new String[1000]; 
		String linha;
		int quant = 0; 

		buff = new BufferedReader(new FileReader("../db/agenda.txt"));
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
		
		Scanner scan = new Scanner(new File("../db/agenda.txt"));
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
		while (!agenda[i].equals(linha)) {
			i++;
		}
		agenda[i] = null;
		
		while(i < agenda.length-1) {
			agenda[i] = agenda[i+1];
			i++;
		}
		
		File file = new File("../db/agenda.txt");
		file.delete();

		BufferedWriter bw = new BufferedWriter(new FileWriter("../db/agenda.txt"));


		try {
			for(int k=0; agenda[k] != null && k<agenda.length; k++) {
				bw.write(agenda[k] + "\n");
			}
		} finally {
			bw.close();
		}
	}
	
	public void marcaAgenda(String data, String horario, String tipo, String idFunc, String idPac) throws FileNotFoundException{
		try {
			String novaLinha = data + ", "+horario+", " + tipo + ", " + idFunc + ", " + idPac;
			Scanner scan = new Scanner(new File("../db/agenda.txt"));
			while(scan.hasNext()){
				String line = scan.nextLine().toString();
				if(line.contains(novaLinha)){
					throw new Exception("Este profissional nao esta disponivel nesta data e horario");
				}
			}
			scan.close();
			br.write(novaLinha);
			br.newLine();
			br.flush();
			System.out.println("Horario agendado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao marcar na agenda: " + e);
		}
	}
		
	public void fecharAgenda() throws IOException {
		br.close();
	}		
}
