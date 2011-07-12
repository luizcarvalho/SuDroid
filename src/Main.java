import br.com.redrails.SuDroid;

public class Main {

	public static void main(String[] args) {
		SuDroid term = new SuDroid("ping -c 4 www.google.com");
		System.out.print("RESPOSTA: "+term.success_msg);
		System.out.print("ERROS: "+term.error_msg);
	}

}
