import java.util.*;
import java.util.ArrayList;

public class NestedStack {
	public static void main(String[]args) {

		Stack<Integer> deck = new Stack<Integer>();

			for (int i = 0; i < 9; i++){
			  int val = (int)(Math.random()*13)+ 1;
			  if (val >= 10)
				  deck.push(10); // 10, J, Q, K worth 10
			  else if (val == 1)
				  deck.push(11); // Ace is worth 11
			  else
				deck.push(val);  //  card values 2 - 9
			}

		System.out.println(deck+"<-- top here");

		ArrayList<Stack<Integer>> hands = new ArrayList<Stack<Integer>>();
		int numPlayers = 3;
		for (int i = 0; i<numPlayers; i++)
			hands.add(new Stack<Integer>());

		while (!deck.empty()) {
			for (int i = 0; i<numPlayers; i++) {
				if (!deck.empty())
					hands.get(i).push(deck.pop());
			}
		}

		for (int i = 0; i<numPlayers; i++) {
			System.out.print("HAND " + i + ": ");
			while (!hands.get(i).empty()) {
				System.out.print(hands.get(i).pop() + " ");
			}
			System.out.println();
		}

	}
}