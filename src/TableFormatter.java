import java.util.regex.*;

public class TableFormatter {
    
    // Instance variables
    
    String original;
    String result;
    
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
    
    int tableWidth() {
        int w;
        for (
    }
}
