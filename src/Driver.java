public class Driver {

	public static void main(String[] args) {
		
		URLtoSource test = new URLtoSource("http://marge.stuy.edu/~brandon.lin/"); // testing url

		System.out.println(test.getSource());

		System.out.println(test.getBody());

		//System.out.println(test.getHead());

		//System.out.println(test.getTitle());

		//String[] ps = test.getParagraphs();

		//for (String p : ps) {
		//	System.out.println(p);
		//}
	}
}
