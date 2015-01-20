import java.util.regex.*;
import java.util.ArrayList;

public class TableFormatter {
	// Take in currentParagraph from URLtoSource.java,
	// Breaks stuff into 2-D ArrayList structure,
	// Then spits it out as one string. I guess.
	
	
	// Instance variables
	
	String STRING;
	String result;
	
	ArrayList<ArrayList<String>> grid;
	
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
		STRING = s;
		result = "";
		
		trSFinder = trPatS.matcher(s);
		trEFinder = trPatE.matcher(s);
		thSFinder = thPatS.matcher(s);
		thEFinder = thPatE.matcher(s);

		grid = new ArrayList<ArrayList<String>>();
		//grid.add(new ArrayList<String>());
		/*
		for (ArrayList<String> foo : grid) {
		    foo = new ArrayList<String>(1);
		}
		*/
	}
	
	// Methods
	
	public String getResult() {
		return result;
	}
	
	void fillLists() {
		trSFinder.matches();
		trEFinder.matches();
		while(trSFinder.find() && trEFinder.find()) {
			grid.add(new ArrayList<String>());
			String tableRow = STRING.substring(trSFinder.end(), trEFinder.start());
			tdSFinder = tdPatS.matcher(tableRow);
			tdSFinder.matches();
			tdEFinder = tdPatE.matcher(tableRow);
			tdEFinder.matches();
			while(tdSFinder.find() && tdEFinder.find()) {
			    String element = tableRow.substring(tdSFinder.end(), tdEFinder.start()).trim() + " ";
			    grid.get(grid.size()-1).add(element); // ugh, there has to be a better way
			}
		}
	}
	
	void blam() {
	    for(ArrayList<String> foo : grid) {
		for(String fooPrime : foo) {
		    result += fooPrime;
		}
		result += "\n";
	    }
	}

	int tableWidth() {
		int w = 0;
		return w;
	}
	
	void doStuff() {
	    System.out.println("WTFFF");
		this.fillLists();
		this.blam();
	}
}

