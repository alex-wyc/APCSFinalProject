public class TestSplit {
	public static void main(String[] args) {
		String str = "<td>a</td>,b,c,d,e,f,,g";
		String[] splitArray = str.split(",");
		for(String s : splitArray) {
			System.out.print(s + " ");
		}
	}
}
