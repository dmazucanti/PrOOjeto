import login.criptog;
import java.util.Scanner;
public class testeHash{
	public static void main(String[] args){
		Scanner s = new Scanner(System.in);
		String a = s.next();
		int b = criptog.hash(a);
		System.out.println(a + " tem o hash " + b);
	}
}
