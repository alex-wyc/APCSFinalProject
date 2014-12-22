public class Format {
	
	// Instance variables

	String original;
	String result;

	// Constructor

	public Format(String s) {
		original = s;
		result = s;
	}

	// Methods

	public String getResult() {
		return result;
	}

	public void setBold() {
		result = "[1m" + result + "[22m";
	}

	public void setItalic() {
		result = "[3m" + result + "[23m";
	}

	public void setUnderline() {
		result = "[4m" + result + "[24m";
	}

	public void setStrikeThrough() {
		result = "[9m" + result + "[29m";
	}

	public void setColor(String color) {
		String ANSI = "[";

		switch(color) {
			case "black":
				ANSI = ANSI + "30m";
				break;

			case "red":
				ANSI = ANSI + "31m";
				break;

			case "green":
				ANSI = ANSI + "32m";
				break;

			case "yellow":
				ANSI = ANSI + "33m";
				break;

			case "blue":
				ANSI = ANSI + "34m";
				break;

			case "magenta":
				ANSI = ANSI + "35m";
				break;

			case "cyan":
				ANSI = ANSI + "36m";
				break;

			case "white":
				ANSI = ANSI + "97m";
				break;

			default:
				ANSI = "";
				break;
		}

		result = ANSI + result + "[0m";
	}
}
