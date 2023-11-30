import java.io.*;
import java.util.*;

public class SonnetMap {
	public static void main(String[]args) {

		File name = new File("Poem.txt");  // File name must match that on computer
		String text,output="";
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}

		} catch (Exception e) { e.printStackTrace(); }

		String [] poem = output.replace("\n", " ").toLowerCase().split(" ");
		/*for (int i = 0; i<poem.length; i++)
			System.out.println(poem[i]);*/

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (String s: poem) {
			char c = s.charAt(0);
			if (map.containsKey(c))
				map.put(c, map.get(c)+1);
			else
      			map.put(c, 1);
		}

		//System.out.println(map);

		Iterator<Map.Entry<Character,Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Integer> entry = it.next();
			if (entry.getValue()%2==0)
				it.remove();
		}

		Set<Map.Entry<Character, Integer>> mapSet = map.entrySet();
	    for (Map.Entry<Character, Integer> entry : mapSet)
		     System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());


		/* Read in this Sonnet about HashMaps (generated by chat GPT of course)  into a HashMap<Character,Integer> that contains the count of all words that start with a given letter.   Ignore capitalization.
		So if there are 7 words that start with 'n' or 'N', n=7 should be in the map.
		Then, iterate over the map and remove all entries with even number counts, so that only characters with odd values remain.
		Finally, print all the entries with one key-value pair per line.   Do not worry about order. */

	}
}