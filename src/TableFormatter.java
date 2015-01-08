import java.util.regex.*;
import java.util.ArrayList;

public class TableFormatter {
    // Take in currentParagraph in URLtoSource.java,
    // Breaks stuff into 2-D ArrayList structure,
    // Then spits it out as one string. I guess.


    // Instance variables
    
    String original;
    String result;
    
    ArrayList< ArrayList<String> > grid = new ArrayList< ArrayList<String> >();

    Pattern tdPatS = Pattern.compile("\\<td.*?\\>", Pattern.CASE_INSENSITIVE);
    Pattern tdPatE = Pattern.compile("\\</td\\>", Pattern.CASE_INSENSITIVE);
    
    
    int thisRowWidth;

    // Constructor
    
    public TableFormatter(String s) {
        original = s;
        result = s;
    }
    
    // Methods
    
    public String getResult() {
	return result;
    }
    
    void demolish() {
        // Fills up the ArrayList thing

    }

    int tableWidth() {
        int w = 0;
        return w;
    }

    void MISSION_CONTROL() {
        // YEP
    }
}
