import java.util.*;
import java.util.regex.*;

public class ListFormatter {
	
	// Instance variables

	ArrayList<String> listEls = new ArrayList<String>();

	// Regex stuff
	Pattern listElPatS = Pattern.compile("\\<li.*?\\>|\\<ul.*?\\>|\\<ol.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern listElPatE = Pattern.compile("\\</li\\>|\\</ul\\>|\\</ol\\>", Pattern.CASE_INSENSITIVE);

	Pattern sublistPatS = Pattern.compile("\\<ul.*?\\>|\\<ol.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern sublistPatE = Pattern.compile("\\</ul\\>|\\</ol\\>", Pattern.CASE_INSENSITIVE);

	// Constructors

	public ListFormatter(String html, boolean ordered) {
		if (ordered) {
			listEls = orderedHandler(html, 0);
		}
		else {
			listEls = unOrderedHandler(html, 0);
		}
	}

	public String getResult() {
		String out = "";

		for (int i = 0 ; i < listEls.size() ; i++) {
			out = out + listEls.get(i) + "\n";
		}
		
		return out;
	}

	private ArrayList<String> orderedHandler(String html, int index) {
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
				out.addAll(orderedHandler(html.substring(sublistBegin, sublistEnd), index + 1));
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
				out.addAll(unOrderedHandler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else {
				// If this is a normal string
				// stupid wikipedia formatting... actually has to check for sublists within <li> -_-
				/* Failed attempt at fixing wikipedia formatting... causes heap space error for some reason
				int start = listElPatFinderS.end();

				while (!(listElPatFinderE.group().substring(2,4).equals("li"))) {
					// i hate wikipedia...
					listElPatFinderS.find();
					listElPatFinderE.find();
				}

				String content = html.substring(start, listElPatFinderE.start());
				Matcher containsSublist = sublistPatS.matcher(content);
				Matcher sublistEnd = sublistPatE.matcher(content);

				ArrayList<String> sublist = null;

				if (containsSublist.find()) {
					sublistEnd.find();
					String furtherHTML = content.substring(containsSublist.end(), sublistEnd.start());
					content = content.substring(0, containsSublist.start());
					if (containsSublist.group().substring(1,3).equals("ul")) {
						sublist = unOrderedHandler(furtherHTML, index + 1);
					}
					else {
						sublist = orderedHandler(furtherHTML, index + 1);
					}
				}
				*/
				String content = html.substring(listElPatFinderS.end(), listElPatFinderE.start());
				content = removeAllElse(content);
				out.add(preamble + counter + ". " + content);
				//out.addAll(sublist);
				counter++;
			}

			elFind = listElPatFinderS.find() && listElPatFinderE.find();
		}

		return out;
	}

	private ArrayList<String> unOrderedHandler(String html, int index) {
//		System.out.println(html);
		String preamble = "";
		for (int i = 0 ; i < index ; i++) {
			preamble = preamble + "\t";
		}
		preamble = preamble + "\u2022";
		switch(index % 4) {
			case 0:
				preamble = "\u2022"; // black bullet
				break;
			case 1:
				preamble = "\u25e6"; // hollow bullet
				break;
			case 2:
				preamble = "\u25AA"; // square bullet
				break;
			case 3:
				preamble = ">. "; // this
				break;
		}

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
				out.addAll(orderedHandler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else if (id.substring(0,3).equals("<ul")) {
				int sublistBegin = listElPatFinderS.end();

				while (!(listElPatFinderE.group().substring(0,4).equals("</ul"))) {
//					System.out.println("\ngroup: " + listElPatFinderE.group() + "\n");
					listElPatFinderS.find();
					listElPatFinderE.find();
					// We skip over the content of the sublist
				}

				int sublistEnd = listElPatFinderE.start();
				// RECURSION!
				out.addAll(unOrderedHandler(html.substring(sublistBegin, sublistEnd), index + 1));
			}

			else {
				// If this is a normal string --> apparently weirdos at wikipedia write like the following:
				//<ul>
				//<li>History
				//<ul>
				//<li>Early History</li>
				//</ul>
				//</li>
				//</ul>
				//HOW TO DEAL WITH THIS?!?!
				// Below is a failed attempt

				String content = html.substring(listElPatFinderS.end(), listElPatFinderE.start());
				content = removeAllElse(content);
				out.add(preamble + " " + content);
				/*

				int start = listElPatFinderS.end();

				boolean found = true;

				while (found) {
					// i hate wikipedia...
					if (listElPatFinderE.group().substring(2,4).equals("li")) {
						break;
					}

					found = listElPatFinderS.find() && listElPatFinderE.find();
				}

				String content = html.substring(start, listElPatFinderE.start());
				Matcher containsSublist = sublistPatS.matcher(content);
				Matcher sublistEnd = sublistPatE.matcher(content);

				ArrayList<String> sublist = null;

				if (containsSublist.find() && sublistEnd.find()) {
					String furtherHTML = content.substring(containsSublist.end(), sublistEnd.start());
					content = content.substring(0, containsSublist.start());
					if (containsSublist.group().substring(1,3).equals("ul")) {
						sublist = unOrderedHandler(furtherHTML, index + 1);
					}
					else {
						sublist = orderedHandler(furtherHTML, index + 1);
					}
				}

				out.add(preamble + content);
				if (sublist != null) {
					out.addAll(sublist);
				}
*/
			}

			elFind = listElPatFinderS.find() && listElPatFinderE.find();
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
