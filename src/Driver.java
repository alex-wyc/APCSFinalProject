import java.util.*;

public class Driver {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		clearScreen();

		URLtoSource site = null;
		
		System.out.println("Welcom to Athenian Rabbit Browser, where would you like to go (URL)?");
		String URLStr = sc.nextLine();

		try {
			site = new URLtoSource(URLStr);
			System.out.println("Loading...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Formatting not yet implemented, program will now exit");
			System.exit(1);
		}

		clearScreen();

		//System.out.println(site.getSource());

		System.out.println(site.getTitle());

		System.out.println();
		
		String[] paragraphs = site.getParagraphs();

		for (String p : paragraphs) {
			System.out.println(p);
			System.out.println();
		}
	}

	public static void clearScreen() {
		System.out.println("\033\143");
	}
}
