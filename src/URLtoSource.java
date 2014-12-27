import java.net.*;
import java.io.*;

public class URLtoSource {

	// Variables
	String source = "";
	String head = "";
	String body = "";
	
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

		if (source.indexOf("</head>") != -1) {
			head = source.substring(source.lastIndexOf("<head>") + 6, source.indexOf("</head>"));
		}

		else if (source.indexOf("</HEAD>") != -1) {
			head = source.substring(source.lastIndexOf("<HEAD>") + 6, source.indexOf("</HEAD>"));
		}

		if (source.indexOf("</body>") != -1) {
			body = source.substring(source.lastIndexOf("<body>") + 6, source.indexOf("</body>"));
		}

		else if (source.indexOf("</BODY>") != -1) {
			body = source.substring(source.lastIndexOf("<BODY>") + 6, source.indexOf("</BODY>"));
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
}
