import java.util.*;

public class DoNow1101{
	public static void main (String[]args) {

	/*  Given String sentence = "this is a nice sentence with no punctuation";  load the individual words onto a Stack<String>, then pull the strings off the stack to write the sentence in reverse.  */

		String sentence = "this is a nice sentence with no punctuation";
		Stack<String> stack = new Stack<String>();
		String[] words = sentence.split(" ");

		for (int i = 0; i < words.length; i++) {
			stack.push(words[i]);
		}

		while (!stack.empty()) {
			System.out.print(stack.pop() + " ");
		}

		System.out.println();


	 }
}