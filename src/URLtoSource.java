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
	
	// Regex stuff for finding head and body and stuff
	Pattern headPatS = Pattern.compile("\\<head.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern headPatE = Pattern.compile("\\</head\\>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPatS = Pattern.compile("\\<body.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern bodyPatE = Pattern.compile("\\</body\\>", Pattern.CASE_INSENSITIVE);
	Pattern titlePatS = Pattern.compile("\\<title.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern titlePatE = Pattern.compile("\\</title\\>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPatS = Pattern.compile("\\<p.*?\\>|\\<h[1-9].*?\\>|\\<ul.*?\\>|\\<ol.*?\\>|\\<table.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern paragraphPatE = Pattern.compile("\\</p\\>|\\</h[1-9]\\>|\\</ul\\>|\\</ol\\>|\\</table\\>", Pattern.CASE_INSENSITIVE);
	
	// Regex patterns for finding <table> stuff
	// Placed here instead of line 140 just because
	Pattern thPatS = Pattern.compile("\\<th.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern thPatE = Pattern.compile("\\</th\\>", Pattern.CASE_INSENSITIVE);
	Pattern trPatS = Pattern.compile("\\<tr.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern trPatE = Pattern.compile("\\</tr\\>", Pattern.CASE_INSENSITIVE);
	Pattern tdPatS = Pattern.compile("\\<td.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern tdPatE = Pattern.compile("\\</td\\>", Pattern.CASE_INSENSITIVE);
	
	// Constructors
	public URLtoSource(String site) throws Exception {
		
		try {
			
			URL siteURL = new URL(site);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(siteURL.openStream()));
			
			String inputLine;
			
			while ((inputLine = br.readLine()) != null) {
				source = source + " " + inputLine.replaceAll("\t", "").replaceAll("<br>", "\n");
			}

			source = source.replace("  ", " ");
			
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
			int initial = paragraphSFinder.end();
			
			String pstarter = paragraphSFinder.group();
			System.out.println(pstarter);

			int end = paragraphEFinder.start();
/*
			while (!(paragraphEFinder.group().substring(2,4).equals(pstarter.substring(1,3)))) {
				end = paragraphEFinder.start();
				paragraphSFinder.find();
				if (!(paragraphEFinder.find())) {
					break;
				}
			}
*/

			int startCounter = 1;
			int endCounter = 0;

			boolean SI = true;
			boolean EI = true;
			
			// Nested html = pestilance urafjkfhkajhf
			// Problem: cannot distinguish between consecutive and nested statements!!??!#fajksdgf

			while ((startCounter != endCounter) && pFind) {
				System.out.println(startCounter + ", " + endCounter);
				System.out.println(pstarter + ", " + paragraphEFinder.group());
				SI = paragraphSFinder.find();

				if (paragraphEFinder.group().substring(2,4).equals(pstarter.substring(1,3))) {
					endCounter++;
					end = paragraphEFinder.start();
				}

				if (SI) {

					if (paragraphSFinder.group().substring(1,3).equals(pstarter.substring(1,3)) && paragraphSFinder.start() < paragraphEFinder.start()) {
						startCounter++;
					}

				}

				EI = paragraphEFinder.find();

				System.out.println("SI: " + SI + ", EI: " + EI);

				pFind = EI && SI;
			}


			currentParagraph = body.substring(initial, end);
			System.out.println(currentParagraph);

			if (pstarter.substring(0,2).equals("<p")) {
				System.out.println("YOOOOOOOOO");
				ParagraphFormatter pf = new ParagraphFormatter(currentParagraph);
				currentParagraph = pf.getResult();
			}
			
			else if (pstarter.substring(0,2).equals("<h")) {
				Format pf = new Format(currentParagraph);
				if (pstarter.charAt(2) < '5') {
					pf.setBold();
				}
				else {
					pf.setUnderline();
				}
				
				currentParagraph = removeAllElse(pf.getResult());
			}
			
			else if (pstarter.substring(0,3).equals("<ul")) {
				
				ListFormatter pf = new ListFormatter(currentParagraph, false);
				
				currentParagraph = pf.getResult();
			}
			
			else if (pstarter.substring(0,3).equals("<ol")) {
				ListFormatter pf = new ListFormatter(currentParagraph, true);
				
				currentParagraph = pf.getResult();
			}

			// "<table" is way too long, causes index-out-of-bound
			else if (pstarter.substring(0,3).equals("<ta")) {
				TableFormatter pf = new TableFormatter(currentParagraph);
				pf.doStuff();
				
				currentParagraph = pf.getResult();
            }

			paragraphs.add(currentParagraph);

			//pFind = paragraphSFinder.find() && paragraphEFinder.find();
		
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

	private String removeAllElse(String html) {

		while (html.contains("<") && html.contains(">")) {
			html = html.substring(0, html.indexOf("<")) + html.substring(html.indexOf(">") + 1);
		}

		return html;
	}
}
