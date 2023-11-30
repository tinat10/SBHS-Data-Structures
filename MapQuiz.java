import java.util.*;
import java.io.*;

public class MapQuiz {
	public static void main(String[]args) {

		String [] words = {"hat", "bat", "clap", "bit", "bop", "stop"};
		int num = midCharCount(words);

		System.out.println("NUMBER: " + num);
	}

	public static int midCharCount(String [] array) {
		int num = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		for (int i = 0; i<array.length; i++) {
			int index = array[i].length()/2;
			char temp = array[i].charAt(index);
			if (map.containsKey(temp)) {
				map.put(temp, map.get(temp)+1);
			}
			else
				map.put(temp, 1);
		}

		for (Map.Entry<Character, Integer> e: map.entrySet()) {
			if (e.getValue() > 1)
				num+=e.getValue();
		}

		return num;
	}
}