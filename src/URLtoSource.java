import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.ArrayList;

public class URLtoSource {

	// Variables
	String source = "";
	String head = "";
	String body = "";
	String title = "";
	ArrayList<String> paragraphs = new ArrayList<String>();

	// Reg ex stuff for finding head and body
	Pattern headPat = Pattern.compile("<head.*>.*</head>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPat = Pattern.compile("<body.*>.*</body>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPat = Pattern.compile("<p.*>.*</p>", Pattern.CASE_INSENSITIVE);
	Pattern titlePat = Pattern.compile("<title.*>.*</title>", Pattern.CASE_INSENSITIVE);
	
	// Constructors
	public URLtoSource(String site) {

		try {

			URL siteURL = new URL(site);

			BufferedReader br = new BufferedReader(new InputStreamReader(siteURL.openStream()));

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				source = source + inputLine;
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Matcher headFinder = headPat.matcher(source);
		head = headFinder.group();

		System.out.println("Head found");

		Matcher bodyFinder = bodyPat.matcher(source);
		body = bodyFinder.group();

		System.out.println("Body found");

		Matcher paragraphFinder = paragraphPat.matcher(body);

		while (paragraphFinder.find()) {
			paragraphs.add(paragraphFinder.group());
		}

		Matcher titleFinder = titlePat.matcher(head);
		title = titleFinder.group();
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
