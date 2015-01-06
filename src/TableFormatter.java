public class TableFormatter {
    
    // Instance variables
    
    String original;
    String result;
    
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
    
    
}
