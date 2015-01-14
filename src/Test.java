import java.util.regex.*;

public class Test {

	public static void main(String[] args) {
		String SampleCode = "<li>UNORDERED1</li><li>UNORDERED2</li><ol><li>ORDERED ONE</li><li>ORDERED TWO</li></ol>";
		ListFormatter lf = new ListFormatter(SampleCode, false);
		System.out.println("RESULT:");
		System.out.println(lf.getResult());
	}
}
