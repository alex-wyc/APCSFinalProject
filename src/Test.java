import java.util.regex.*;

public class Test {

	public static void main(String[] args) {
		String SampleCode = "<li>HELLO</li><li>HELLO2</li><ul><li>UNORDERED TEST!</li></ul>";
		ListFormatter lf = new ListFormatter(SampleCode, true);
		System.out.println("RESULT:");
		System.out.println(lf.getResult());
	}
}
