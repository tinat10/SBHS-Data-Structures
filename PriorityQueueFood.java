import java.util.*;
import java.io.*;


public class PriorityQueueFood {

	public static void main (String[]args) {

		String text, output = "";
		String [] food = new String[24];

		@SuppressWarnings("unchecked")
		PriorityQueue<String> [] pq = new PriorityQueue[24];

		Queue<String> q = new PriorityQueue();

		try {

			BufferedReader input = new BufferedReader(new FileReader("foods.txt"));  // This object reads text line-by-line

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}

			food = output.split("\n");

		} catch (Exception e) {
			System.out.println("ERROR CHOPPING DATA");
		}

		for (int i = 0; i<24; i++) {
			pq[i] = new PriorityQueue<String>();
		}


		for (int i = 0; i<food.length; i++) {

			//System.out.println(food[i]);
			String f = food[i];
			pq[f.length()-1].add(f);

		}


		/* prints out all foods in alphabetical order. is sorted in terms of word length already
		for (int i = 0; i<pq.length; i++) {
			while (!pq[i].isEmpty())
				System.out.print(pq[i].poll() + ", ");
			System.out.println("\n\n");
		} //*/

		for (int i = 0; i<11; i++) // to get the 12th word, i need to pull 11 out first
			pq[6].poll();
		System.out.println("WORD: " + pq[6].poll());

	}

}