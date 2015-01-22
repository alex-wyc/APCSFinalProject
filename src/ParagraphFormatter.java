import java.util.*;
import java.util.regex.*;
import org.apache.commons.lang3.StringEscapeUtils;

public class ParagraphFormatter {
	// Variables
	String result = null;

	// RegEx stuff
	Pattern boldElPatS = Pattern.compile("\\<b\\>|\\<strong\\>", Pattern.CASE_INSENSITIVE);
	Pattern boldElPatE = Pattern.compile("\\</b\\>|\\</strong\\>", Pattern.CASE_INSENSITIVE);

	Pattern italicElPatS = Pattern.compile("\\<i\\>|\\<em\\>", Pattern.CASE_INSENSITIVE);
	Pattern italicElPatE = Pattern.compile("\\</i\\>|\\</em\\>", Pattern.CASE_INSENSITIVE);

	Pattern deletedElPatS = Pattern.compile("\\<del\\>", Pattern.CASE_INSENSITIVE);
	Pattern deletedElPatE = Pattern.compile("\\</del\\>", Pattern.CASE_INSENSITIVE);

	Pattern insertedElPatS = Pattern.compile("\\<ins\\>", Pattern.CASE_INSENSITIVE);
	Pattern insertedElPatE = Pattern.compile("\\</ins\\>", Pattern.CASE_INSENSITIVE);

	Pattern hyperlinkedPatS = Pattern.compile("\\<a.*?href.*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern hyperlinkedPatC = Pattern.compile("\\<a.*?href=\"(.*?)\".*?\\>", Pattern.CASE_INSENSITIVE);
	Pattern hyperlinkedPatE = Pattern.compile("\\</a\\>", Pattern.CASE_INSENSITIVE);

	// Constructor! (One function that solves everything)
	public ParagraphFormatter(String html) {
		result = setAllBold(html);
		result = setAllItalic(result); // Apparently not achievable in gnome terminal, someone try this with windows pl0x
		result = setAllUnderline(result);
		result = setAllStrikethrough(result);
		result = hyperlinked(result);

		result = removeAllElse(result);

		result = handleHTMLChars(result);
	}

	// Methods
	public String getResult() {
		return result;
	}

	private String hyperlinked(String html) {
		String output = "";

		Matcher hyperlinkPatFinderS = hyperlinkedPatS.matcher(html);
		Matcher hyperlinkContent = hyperlinkedPatC.matcher(html);
		Matcher hyperlinkPatFinderE = hyperlinkedPatE.matcher(html);

		boolean isHyperlink = hyperlinkPatFinderS.find() && hyperlinkPatFinderE.find() && hyperlinkContent.find();
		int lastIndex = 0;

		if (!(isHyperlink)) {
			return html;
		}

		while (isHyperlink) {
			output = output + html.substring(lastIndex, hyperlinkPatFinderS.start());

			Format toUnderline = new Format(html.substring(hyperlinkPatFinderS.end(), hyperlinkPatFinderE.start()));
			toUnderline.setUnderline();
			String hyperlinkTarget = toUnderline.getResult();
			hyperlinkTarget += " (link:" + hyperlinkContent.group(1) + ") ";

			output = output + hyperlinkTarget;
			lastIndex = hyperlinkPatFinderE.end();

			isHyperlink = hyperlinkPatFinderS.find() && hyperlinkPatFinderE.find() && hyperlinkContent.find();
		}

		output += html.substring(lastIndex);

		return output;
	}

	private String setAllBold(String html) {
		String output = "";

		Matcher boldElPatFinderS = boldElPatS.matcher(html);
		Matcher boldElPatFinderE = boldElPatE.matcher(html);

		boolean bFind = boldElPatFinderS.find() && boldElPatFinderE.find();
		int lastIndex = 0;

		if (!(bFind)) {
			return html;
		}

		while (bFind) {
			output = output + html.substring(lastIndex, boldElPatFinderS.start());

			Format bold = new Format(html.substring(boldElPatFinderS.end(), boldElPatFinderE.start()));
			bold.setBold();

			output = output + bold.getResult();
			lastIndex = boldElPatFinderE.end();

			bFind = boldElPatFinderS.find() && boldElPatFinderE.find();
		}

		output = output + html.substring(lastIndex);

		return output;
	}

	private String setAllItalic(String html) {
		String output = "";

		Matcher italicElPatFinderS = italicElPatS.matcher(html);
		Matcher italicElPatFinderE = italicElPatE.matcher(html);

		boolean iFind = italicElPatFinderS.find() && italicElPatFinderE.find();
		int lastIndex = 0;

		if (!(iFind)) {
			return html;
		}

		while (iFind) {
			output = output + html.substring(lastIndex, italicElPatFinderS.start());

			Format italic = new Format(html.substring(italicElPatFinderS.end(), italicElPatFinderE.start()));
			italic.setItalic();

			output = output + italic.getResult();
			lastIndex = italicElPatFinderE.end();

			iFind = italicElPatFinderS.find() && italicElPatFinderE.find();
		}

		output = output + html.substring(lastIndex);

		return output;
	}

	private String setAllUnderline(String html) {
		String output = "";

		Matcher insertedElPatFinderS = insertedElPatS.matcher(html);
		Matcher insertedElPatFinderE = insertedElPatE.matcher(html);

		boolean iFind = insertedElPatFinderS.find() && insertedElPatFinderE.find();
		int lastIndex = 0;

		if (!(iFind)) {
			return html;
		}

		while (iFind) {
			output = output + html.substring(lastIndex, insertedElPatFinderS.start());

			Format underline = new Format(html.substring(insertedElPatFinderS.end(), insertedElPatFinderE.start()));
			underline.setUnderline();

			output = output + underline.getResult();
			lastIndex = insertedElPatFinderE.end();

			iFind = insertedElPatFinderS.find() && insertedElPatFinderE.find();
		}

		output = output + html.substring(lastIndex);

		return output;
	}

	private String setAllStrikethrough(String html) {
		String output = "";

		Matcher deletedElPatFinderS = deletedElPatS.matcher(html);
		Matcher deletedElPatFinderE = deletedElPatE.matcher(html);

		boolean dFind = deletedElPatFinderS.find() && deletedElPatFinderE.find();
		int lastIndex = 0;

		if (!(dFind)) {
			return html;
		}

		while (dFind) {
			output = output + html.substring(lastIndex, deletedElPatFinderS.start());

			Format strikethrough = new Format(html.substring(deletedElPatFinderS.end(), deletedElPatFinderE.start()));
			strikethrough.setStrikeThrough();

			output = output + strikethrough.getResult();
			lastIndex = deletedElPatFinderE.end();

			dFind = deletedElPatFinderS.find() && deletedElPatFinderE.find();
		}

		output = output + html.substring(lastIndex);

		return output;

	}

	private String removeAllElse(String html) {
		String output = html;

		while (output.contains("<") && output.contains(">")) {
			output = output.substring(0, output.indexOf("<")) + output.substring(output.indexOf(">") + 1);
		}

		return output;
	}

	private String handleHTMLChars(String html) {
		//return html.replaceAll("&ndash;", "-").replaceAll("&mdash;", "--").replaceAll("&quot;|&ldquo;|&rdquo;", "\"").replaceAll("&lsquo;|&rsquo;", "\'").replaceAll("&gt;", ">").replaceAll("&lt;", "<");
		return new StringEscapeUtils().unescapeHtml4(html);
	}

}
