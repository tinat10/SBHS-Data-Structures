import java.util.*;
import java.io.*;

public class TreeSubsets {
  public static void main(String[] args) {


	String [] words = new String[2];

	try
	{
		BufferedReader input = new BufferedReader(new FileReader("words.txt"));  // This object reads text line-by-line
		String word = "";
		String output="";
		while((word=input.readLine())!= null) // Keep reading until end of file (null)
		{
			output+=word + "\n";
		}

		words = output.split("\n");
	}
	catch (IOException io)
	{
			System.err.println("Error reading file => "+io);
	}

	TreeSet<String> set = new TreeSet<String>();
	for (String s: words)
		set.add(s);

	set = (TreeSet<String>) set.subSet("thing", true, "throw", true);

	Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
	}

     /* Task:  Load the 5 letter "wordle" words from the file at this url:

	    https://www-cs-faculty.stanford.edu/~knuth/sgb-words.txt

	    and put them in a TreeSet.  Use the subset method to find and print out
	    all the words from "thing" to "throw" INCLUSIVE.   Be careful to include
	    both "thing" and "throw" in your final subset (if not present from method
	    call add back to complete the subset).

	    Here is a reference that includes documentation of subSet method:
	    https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html

	    You can use the code below to help you start more quickly
    */



	 /*
    TreeSet<Integer> ts = new TreeSet<Integer>();
    for (int i = 0; i < 30; i++)
      ts.add((int)(Math.random()*100+1));

    // Create a subset of all elements >= 80
    TreeSet<Integer> tail = (TreeSet<Integer>)ts.tailSet(80);

    // Print the new Set
    for (int i : tail)
      System.out.print(i+" ");
    System.out.println();
	*/





  }
}