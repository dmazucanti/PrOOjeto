import agenda.*;

import java.io.IOException;

public class testeAgenda
{
	public static void main(String args[]) {
		Agenda agenda = null;
		try { 
			agenda = Agenda.getInstance();
		} catch (IOException e) {
			System.out.println("Erro1");
			System.out.println(e);
		}
		try {
			agenda.marcaAgenda("01/08", "10:00", "consulta", "20002", "10002");
			agenda.marcaAgenda("01/08", "11:00", "consulta", "20001", "10001");
			agenda.visualizaAgenda();
			agenda.desmarcaAgenda("10001", "01/08", "11:00");
		} catch (Exception e) {
			System.out.println("Erro2");
			System.out.println(e);
		}
	}
}

