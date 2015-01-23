import java.util.*;
import java.awt.Desktop;
import java.net.URI;

public class AthenianRabbitBrowser {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		clearScreen();
		
		URLtoSource site = null;
		
		System.out.println("Welcome to Athenian Rabbit Browser. Where would you like to go (URL)?");
		String URLStr = sc.nextLine();
		
		try {
			System.out.println("Loading...");
			site = new URLtoSource(URLStr);
			
			clearScreen();
			
			//System.out.println(site.getSource());
			
			System.out.println(site.getTitle());
			
			System.out.println();
			
			String[] paragraphs = site.getParagraphs();
			
			for (String p : paragraphs) {
				System.out.println(p);
				System.out.println();
			}
		} catch (Exception e) {
		    try {
			Desktop.getDesktop().browse(new URI(URLStr));
		    } catch (Exception f) {
			System.out.println("Now, you're really screwed.");
			f.printStackTrace();
			System.exit(1);
		    }
		}
	}
	
	public static void clearScreen() {
		System.out.println("\033\143");
	}
}
