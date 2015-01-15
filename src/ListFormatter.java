import java.util.*;
import java.util.regex.*;

public class ListFormatter{
	
	// Instance variables

	ArrayList<String> listEls = new ArrayList<String>();

	// Regex stuff
	Pattern listElPatS = Pattern.compile("\\<li.*?\\>|\\<ul.*?\\>|\\<ol.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern listElPatE = Pattern.compile("\\</li\\>|\\</ul\\>|\\</ol\\>", Pattern.CASE_INSENSITIVE);

	// Constructors

	public ListFormatter(String html, boolean ordered) {
		if (ordered) {
			listEls = orderedHandeler(html, 0);
		}
		else {
			listEls = unOrderedHandeler(html, 0);
		}
	}

	public String getResult() {
		String out = "";

		for (int i = 0 ; i < listEls.size() ; i++) {
			out = out + listEls.get(i) + "\n";
		}
		
		return removeAllElse(out);
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

			if (id.substring(0,3).equals("<ol")) {
				int sublistBegin = listElPatFinderS.end();
				
				while (!(listElPatFinderE.group().substring(0,4).equals("</ol"))) {
					listElPatFinderS.find();
					listElPatFinderE.find();
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();

				// RECURSION!
				out.addAll(orderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else if (id.substring(0,3).equals("<ul")) {
				int sublistBegin = listElPatFinderS.end();

				while (!(listElPatFinderE.group().substring(0,4).equals("</ul"))) {
					listElPatFinderS.find();
					listElPatFinderE.find();

					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();
				// RECURSION!
				out.addAll(unOrderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else {
				// If this is a normal string
				String content = html.substring(listElPatFinderS.end(), listElPatFinderE.start());
				out.add(preamble + counter + ". " + content);
				counter++;
			}

			elFind = listElPatFinderS.find() && listElPatFinderE.find();
		}

		return out;
	}

	private ArrayList<String> unOrderedHandeler(String html, int index) {
		String preamble = "";
		for (int i = 0 ; i < index ; i++) {
			preamble = preamble + "\t";
		}
		preamble = preamble + "\u2022";

		ArrayList<String> out = new ArrayList<String>();

		Matcher listElPatFinderS = listElPatS.matcher(html);
		Matcher listElPatFinderE = listElPatE.matcher(html);

		boolean elFind = listElPatFinderS.find() && listElPatFinderE.find();

		while (elFind) {

			String id = listElPatFinderS.group();

			if (id.substring(0,3).equals("<ol")) {
				int sublistBegin = listElPatFinderS.end();
				
				while (!(listElPatFinderE.group().substring(0,4).equals("</ol"))) {
					listElPatFinderS.find();
					listElPatFinderE.find();
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();

				// RECURSION!
				out.addAll(orderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else if (id.substring(0,3).equals("<ul")) {
				int sublistBegin = listElPatFinderS.end();

				while (!(listElPatFinderE.group().substring(0,4).equals("</ul"))) {
					listElPatFinderS.find();
					listElPatFinderE.find();

					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();
				// RECURSION!
				out.addAll(unOrderedHandeler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else {
				// If this is a normal string
				String content = html.substring(listElPatFinderS.end(), listElPatFinderE.start());
				out.add(preamble + " " + content);
			}

			elFind = listElPatFinderS.find() && listElPatFinderE.find();

			System.out.println(out.toString());

		}

		return out;
	}

	private String removeAllElse(String html) {

		while (html.contains("<") && html.contains(">")) {
			html = html.substring(0, html.indexOf("<")) + html.substring(html.indexOf(">") + 1);
		}

		return html;
	}
}
