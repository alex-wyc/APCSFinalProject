import java.util.regex.*;
import java.util.ArrayList;

public class TableFormatter {
	// Take in currentParagraph from URLtoSource.java,
	// Breaks stuff into 2-D ArrayList structure,
	// Then spits it out as one string. I guess.
	
	
	// Instance variables
	
	String original;
	String result;
	
	ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
	
	Pattern trPatS = Pattern.compile("\\<tr.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern trPatE = Pattern.compile("\\</tr\\>", Pattern.CASE_INSENSITIVE);
	Pattern thPatS = Pattern.compile("\\<th.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern thPatE = Pattern.compile("\\</th\\>", Pattern.CASE_INSENSITIVE);
	Pattern tdPatS = Pattern.compile("\\<td.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern tdPatE = Pattern.compile("\\</td\\>", Pattern.CASE_INSENSITIVE);
	
	Matcher trSFinder;
	Matcher trEFinder;
	Matcher thSFinder;
	Matcher thEFinder;
	Matcher tdSFinder;
	Matcher tdEFinder;
	
	int thisRowWidth;
	
	// Constructor
	
	public TableFormatter(String s) {
		original = s;
		result = s;
		
		trSFinder = trPatS.matcher(s);
		trEFinder = trPatE.matcher(s);
		thSFinder = thPatS.matcher(s);
		thEFinder = thPatE.matcher(s);
		tdSFinder = tdPatS.matcher(s);
		tdEFinder = tdPatE.matcher(s);
	}
	
	// Methods
	
	public String getResult() {
		return result;
	}
	
	void fillLists() {
		while()
	}
	
	int tableWidth() {
		int w = 0;
		return w;
	}
	
	void doStuff() {
	this.fillLists();
	}
}

