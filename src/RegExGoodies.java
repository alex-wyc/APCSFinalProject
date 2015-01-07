import java.util.*;

public RegExGoodies {
	// pseudo-class for static reg-ex goodies.

	public static String replace(String original, String regExPat, String replacement) {
		Pattern pat = Pattern.compile(regExPat);
		Matcher match = pat.matcher(original);
		String out = "";

		try {
			int replaceS = match.start();
			int replaceE = match.end();
			out = original.substring(0,replaceS) + replacement + original.substring(replaceE);
			return out;
		}
		catch {
			return original;
		}
	}

	public static String replaceAll(String original, String regExPat, String replacement) {
		Pattern pat = Pattern.compile(regExPat);
		String out = original;
		Matcher match = pat.matcher(out);

		try {
			int replaceS, replaceE;

			while (match.find()) {
				replaceS = match.start();
				replaceE = match.end();
				out = out.substring(0,replaceS) + replacement + out.substring(replaceE);
			}

			return out;
		}
		catch {
			return original
		}
	}
}
