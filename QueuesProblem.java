import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.*;
import java.io.*;

public class QueuesProblem{

	public static void main(String[]args) {

		Queue<String> q = new LinkedList<String>();
		Queue<String> pq = new PriorityQueue<String>();

		String[] names = new String[2];

		File name = new File("randomNames.txt");  // File name must match that on computer
		try {
			BufferedReader input = new BufferedReader(new FileReader(name));  // This object reads text line-by-line
			String text,output="";

			while((text=input.readLine())!= null) { // Keep reading until end of file (null)
				output+=text +"\n";  // Put into big String for now, mostly you will break into parts
			}

			names = output.split("\n");

		} catch (Exception e) {}

		for (int i = 0; i<names.length; i++) {
			q.add(names[i]);
			pq.add(names[i]);
		}

		System.out.println("QUEUES\t\tPRIORITY QUEUES");

		while (!q.isEmpty()) {
			System.out.println(q.poll() + "\t\t" + pq.poll());
		}

	}

/*
Load the random names in the attached file into both  a Queue and Priority.  Then use the data to create a table with the number of position in queue, person in regular Queue, person in PriorityQueue.
*/

}