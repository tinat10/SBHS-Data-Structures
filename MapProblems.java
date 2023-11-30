import java.util.*;

public class MapProblems {
	public static void main(String[]args) {

		String s = "1.61803398874989484820458683436563811772030917980576286213544862270526046281890244970720720418939113748475408807538689175212663386222353693179318006076672635";
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 2; i<s.length()-1; i++) {
			String sub = s.substring(i, i+2);
			if (map.containsKey(sub))
				map.put(sub, map.get(sub)+1);
			else
				map.put(sub, 1);
		}

		System.out.println("Size: " + map.size() + "\n");
		int lineCount = 0;
		for (Map.Entry<String, Integer> e: map.entrySet()) {

			System.out.print(e.getKey() + ", " + e.getValue() + "   ");
			lineCount++;

			if (lineCount==10) {
				System.out.println();
				lineCount = 0;
			}
		}

		System.out.println("\n\nNumbers repeated more than 4 times:");

		for (Map.Entry<String, Integer> e: map.entrySet()) {
			if (e.getValue() > 4)
				System.out.println(e.getKey() + ", " + e.getValue());

		}

	}
}