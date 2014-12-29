import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;

public class URLtoSource {

	// Variables
	int start, end;
	String source = "";
	String head = "";
	String body = "";
	String title = "";
	ArrayList<String> paragraphs = new ArrayList<String>();

	// Reg ex stuff for finding head and body
	Pattern headPatS = Pattern.compile("\\<head*\\>", Pattern.CASE_INSENSITIVE);
	Pattern headPatE = Pattern.compile("\\</head\\>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPatS = Pattern.compile("\\<body*\\>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPatE = Pattern.compile("\\</body\\>", Pattern.CASE_INSENSITIVE);
	Pattern titlePatS = Pattern.compile("\\<title*\\>", Pattern.CASE_INSENSITIVE);
	Pattern titlePatE = Pattern.compile("\\</title\\>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPatS = Pattern.compile("\\<p*\\>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPatE = Pattern.compile("\\</p\\>", Pattern.CASE_INSENSITIVE);
	
	// Constructors
	public URLtoSource(String site) {

		try {

			URL siteURL = new URL(site);

			BufferedReader br = new BufferedReader(new InputStreamReader(siteURL.openStream()));

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				source = source + inputLine + "\n";
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//System.out.println(source);

		Matcher headSFinder = headPatS.matcher(source);

		if (headSFinder.find()) {
			start = headSFinder.end();
		}

		Matcher headEFinder = headPatE.matcher(source);

		if (headEFinder.find()) {
			end = headEFinder.start();
		}

		head = source.substring(start, end);

		start = end = 0;

		Matcher bodySFinder = bodyPatS.matcher(source);
		
		if (bodySFinder.find()) {
			start = bodySFinder.end();
		}

		Matcher bodyEFinder = bodyPatE.matcher(source);

		if (bodyEFinder.find()) {
			end = bodyEFinder.start();
		}

		body = source.substring(start,end);

		start = end = 0;

		Matcher titleSFinder = titlePatS.matcher(head);

		if (titleSFinder.find()) {
			start = titleSFinder.end();
		}

		Matcher titleEFinder = titlePatE.matcher(head);

		if (titleEFinder.find()) {
			end = titleEFinder.start();
		}

		title = head.substring(start,end);

		Matcher paragraphSFinder = paragraphPatS.matcher(body);
		Matcher paragraphEFinder = paragraphPatE.matcher(body);

		while (paragraphSFinder.find() && paragraphEFinder.find()) {
			paragraphs.add(body.substring(paragraphSFinder.end(), paragraphEFinder.start()));
		}
	}

	// Methods
	public String getSource() {
		return source;
	}

	public String getHead() {
		return head;
	
	}

	public String getBody() {
		return body;
	}

	public String getTitle() {
		return title;
	}

	public String[] getParagraphs() {
		String[] temp = new String[paragraphs.size()];
		paragraphs.toArray(temp);

		return temp;
	}
}
