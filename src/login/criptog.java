package login;

public class criptog{

	public static int hash(String entrada){
		int out = 13;
		int size = entrada.length();

		for (int i=0; i<size; i++){
			out = out*67 + entrada.charAt(i);
		}
		return out;
	}
}

