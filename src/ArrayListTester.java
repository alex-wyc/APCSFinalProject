import java.util.ArrayList;

public class ArrayListTester {
    static ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
    public static void main(String[] args) {
	initList();
	print();
	grid.get(0).add("HELLO");
    }
    static void print() {
	for (ArrayList<String> foo : grid) {
	    for (String item : foo) {
		System.out.print(item + " ");
	    }
	}
    }
    static void initList() {
	for (ArrayList<String> foo : grid) {
	    foo = new ArrayList<String>();
	}
    }
}
