import java.util.*;
import java.io.*;

public class HEAPDoNow {
	public static void main(String[]args) {

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i =0; i<15; i++) {
			pq.add((int)(Math.random()*90)+10);
		}

		Iterator it = pq.iterator();
		while (it.hasNext())
			System.out.print(it.next() + " ");

		System.out.println("\n" + pq);

		while (!pq.isEmpty())
			System.out.print(pq.poll() + " ");

	}
}