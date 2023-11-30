import java.util.*;

public class Sets {
	public static void main (String[]args) {

	// #1

		String fullNum = "1562836047588678243489701526384857673";
		HashSet<Integer> hs = new HashSet<Integer>();
		int index = 0;

		while (hs.size() != 10) {
			int num = Character.getNumericValue(fullNum.charAt(index));
			if (!hs.contains(num))
				hs.add(num);
			index++;
		}

		System.out.println("The set is completed 0-9 at index " + (index-1));


	// #2

		ArrayList<Integer> times = new ArrayList<Integer>();

		for (int i = 0; i<100; i++) {
			int time = 0;
			HashSet<Integer> bday = new HashSet<Integer>();

			while (bday.size() != 365) {
				int day = (int)(Math.random()*365)+1;
				while (bday.contains(day)){
					day = (int)(Math.random()*365)+1;
					time++;
				}
				bday.add(day);
			}

			times.add(time);
		}

		int total = 0;
		for (int i = 0; i<times.size(); i++) {
			total+=times.get(i);
		}

		System.out.println("The average number of times to meet a person born on each day of the year is " + (total/100));


	// #3

		TreeSet<String> alpha = new TreeSet<String>();
		String text = "From the reef-fringed coast of New Caledonia, the Coral Sea stretches into the South Pacific. Slender native pines, listing like whimsical Christmas trees, punctuate the shoreline. The landscape, one of the most biodiverse on the planet, is astonishingly beautiful until the crest of a hill where a different vista unfolds: a gouged red earth pierced by belching smokestacks and giant trucks rumbling across the lunar-like terrain. This is Goro, the largest nickel mine on a tiny French territory suspended between Australia and Fiji that may hold up to a quarter of the world's nickel reserves. It also poses a critical test for Tesla, the world's largest electric vehicle maker, which wants to take control of its supply chain and ensure that the minerals used for its car batteries are mined in an environmentally and socially responsible fashion.";
		text = text.toLowerCase();
		text = text.replace('-', ' ');
		text = text.replace(".", "");
		text = text.replace(",", "");
		//System.out.println(text);

		String [] split = text.split(" ");
		for (int i = 0; i<split.length; i++) {
			alpha.add(split[i]);
		}

		String word = "";
		for (int i = 0; i<14; i++) {
			word = alpha.pollFirst();
		}

		System.out.println("The 14th element is \"" + word + "\".");

	}
}