import java.util.regex.*;

public class Test {

	public static void main(String[] args) {

		String old = "hello world I am Yicheng Wang";
		String regExPat = "Y.*";
		String replacement = "afhjkasdh";
		
		String newStr = RegExGoodies.replace(old, regExPat, replacement);

		System.out.println(newStr);
	}
}
