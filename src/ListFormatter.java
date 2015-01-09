import java.util.*;

public class ListFormatter {
	
	// Instance variables

	ArrayList<String> listEls = new ArrayList<String>();

	// Regex stuff
	Pattern listElPatS = Pattern.compile("\\<li.*?\\>|\\<ul.*?\\>|\\<ol.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern listElPatE = Pattern.compile("\\</li\\>|\\</ul\\>|\\</ol\\>", Pattern.CASE_INSENSITIVE);

	// Constructors

	public ListFormatter(String html, boolean ordered) {
		if (ordered) {
			orderedHandeler(html);
		}
		else {
			unOrderedHandeler(html);
		}
	}

	public String getResult() {
		String out = "";

		for (int i = 0 ; i < listEls.size() ; i++) {
			out = out + listEls.get(i) + "\n";
		}
		
		return out;
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

			String id = listElPatFinderS.group();

			if (id.substring(0,2).equals("<ol")) {
				int sublistBegin = listElPatFinderS.end();
				
				while (!(listElPatFinderE.group().equals("</ol"))) {
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();

				// RECURSION!
				out.addAll(orderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else if (id.substring(0,2).equals("<ul")) {
				int sublistBegin = listElPatFinderS.end();

				while (!(listElPatFinderE.group().equals("</ul"))) {
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();
				// RECURSION!
				out.addAll(unOrderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else {
				// If this is a normal string
				String content = html.substring(listElPatFinderS.end(), listElPatFinderE.start());
				out.add(pramble + counter + ". " + content)
				counter++;
			}
		}

		return out;
	}

	private ArrayList<String> unOrderedHandeler(String html, int index) {
		String preamble = "";
		for (int i = 0 ; i < index ; i++) {
			preamble = preamble + "\t";
		}
		preamble = preamble + "> ";

		ArrayList<String> out = new ArrayList<String>();

		Matcher listElPatFinderS = listElPatS.matcher(html);
		Matcher listElPatFinderE = listElPatE.matcher(html);

		boolean elFind = listElPatFinderS.find() && listElPatFinderE.find();

		while (elFind) {

			String id = listElPatFinderS.group();

			if (id.substring(0,2).equals("<ol")) {
				int sublistBegin = listElPatFinderS.end();
				
				while (!(listElPatFinderE.group().equals("</ol"))) {
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();

				// RECURSION!
				out.addAll(orderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else if (id.substring(0,2).equals("<ul")) {
				int sublistBegin = listElPatFinderS.end();

				while (!(listElPatFinderE.group().equals("</ul"))) {
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();
				// RECURSION!
				out.addAll(unOrderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else {
				// If this is a normal string
				String content = html.substring(listElPatFinderS.end(), listElPatFinderE.start());
				out.add(pramble + counter + ". " + content)
				counter++;
			}
		}

		return out;
	}
}
