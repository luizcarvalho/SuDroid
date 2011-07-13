import br.com.redrails.SuDroid;

public class Main {

	public static void main(String[] args) {
		SuDroid sudroid = new SuDroid("ping -c 4 www.google.com");
		System.out.print(sudroid.result());
	}

}
