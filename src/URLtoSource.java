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

	// Regex stuff for finding head and body and cr@p
	Pattern headPatS = Pattern.compile("\\<head.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern headPatE = Pattern.compile("\\</head\\>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPatS = Pattern.compile("\\<body.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPatE = Pattern.compile("\\</body\\>", Pattern.CASE_INSENSITIVE);
	Pattern titlePatS = Pattern.compile("\\<title.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern titlePatE = Pattern.compile("\\</title\\>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPatS = Pattern.compile("\\<p.*?\\>|\\<h[1-9].*?\\>|\\<ul.*?\\>|\\<ol.*?\\>|\\<table.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPatE = Pattern.compile("\\</p\\>|\\</h[1-9]\\>|\\</ul\\>|\\</ol\\>|\\</table\\>", Pattern.CASE_INSENSITIVE);

    // Regex patterns for finding <table> cr@p
    // Placed here instead of line 140 just because
    Pattern thPatS = Patter.compile("\\<th.*?\\>", Pattern.CASE_INSENSITIVE);
    Pattern thPatE = Patter.compile("\\</th\\>", Pattern.CASE_INSENSITIVE);
    Pattern trPatS = Patter.compile("\\<tr.*?\\>", Pattern.CASE_INSENSITIVE);
    Pattern trPatE = Patter.compile("\\</tr\\>", Pattern.CASE_INSENSITIVE);
    Pattern tdPatS = Patter.compile("\\<td.*?\\>", Pattern.CASE_INSENSITIVE);
    Pattern tdPatE = Patter.compile("\\</td\\>", Pattern.CASE_INSENSITIVE);

	// Constructors
	public URLtoSource(String site) throws Exception {

		try {

			URL siteURL = new URL(site);

			BufferedReader br = new BufferedReader(new InputStreamReader(siteURL.openStream()));

			String inputLine;

			while ((inputLine = br.readLine()) != null) {
				source = source + inputLine.replaceAll("\t", "");
			}

			br.close();
		} catch (Exception e) {
			System.out.println("Corrupt website or site does not exist, program will now exit.");
			System.exit(1);
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

		Format titlef = new Format(title);
		titlef.setBold();
		titlef.setUnderline();
		
		title = titlef.getResult();

		Matcher paragraphSFinder = paragraphPatS.matcher(body);
		Matcher paragraphEFinder = paragraphPatE.matcher(body);

		boolean pFind = paragraphSFinder.find() && paragraphEFinder.find();
		String currentParagraph = null;

		while (pFind) {

			currentParagraph = body.substring(paragraphSFinder.end(), paragraphEFinder.start());

			pstarter = paragraphSFinder.group();

			if (pstarter.substring(0,2).equals("<h")) {
				Format pf = new Format(currentParagraph);
				if (pstarter.charAt(2) < '5') {
					pf.setBold();
				}
				else {
					pf.setUnderline();
				}
			}

			if (pstarter.substring(0,3).equals("<ul") || pstarter.substring(0,3).equals("<ol")) {
				currentParagraph = pstarter + currentParagraph + paragraphEFinder.group();
				ListFormatter pf = new ListFormatter(currentParagraph);
			}
                        
            // table cr@p
            if (pstarter.substring(0,6).equals("<table")) {
                
                TableFormatter pf = new TableFormatter(currentParagraph);
                
                // Dunno how many tables there are???
                while() {
                    Matcher 
                }
            }
                        
			currentParagraph = pf.getResult();

			paragraphs.add(currentParagraph);
			
			pFind = paragraphSFinder.find() && paragraphEFinder.find();
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
