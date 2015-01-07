import java.util.*;

public class ListFormatter {
	
	// Instance variables

	ArrayList<String> listEls = new ArrayList<String>();

	// Regex stuff
	Pattern listElPatS = Pattern.compile("\\<li.*?\\>|\\<ul.*?\\>|\\<ol.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern listElPatE = Pattern.compile("\\</li\\>|\\</ul\\>|\\</ol\\>", Pattern.CASE_INSENSITIVE);

	// Constructors

	public ListFormatter(String html) {

	}

	public String getResult() {

	}

	private ArrayList<String> orderedHandeler(String html, int index) {
		String preamble = "";
		for (int i = 0 ; i < index ; i++) {
			preamble = preamble + "\t";
		}

		ArrayList<String> out = new ArrayList<String>();
		
		int counter = 1;

		Matcher listElPatFinderS = listElPatS.matcher(html);
		Matcher listElPatFinderE = listElPatE.matcher(html);

		boolean elFind = listElPatFinderS.find() && listElPatFinderE.find();

		while (elFind) {

		}
	}

	private ArrayList<String> unOrderedHandeler(String html, int index) {
		String preamble = "";
		for (int i = 0 ; i < index ; i++) {
			preamble = preamble + "\t";
		}
		preamble = preamble + "> ";
	}
}
