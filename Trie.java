/**
Code based on Source code from Baeldung
https://github.com/eugenp/tutorials/tree/master/data-structures

Edited by Chris Haver to make some of the implementation more familiar
to Data Structure Students and to add count attribute to TrieNode.
*/
import java.util.HashMap;
import java.util.Map;

public class Trie {

  private TrieNode root;
  Trie() {
      root = new TrieNode(); // Note root has no
  }

 	public void add(String word) {
	  TrieNode current = root;
	  for (char letter : word.toCharArray()) {
		  Map<Character, TrieNode> kids = current.getChildren();
		  if (kids.containsKey(letter)) {
			  current = kids.get(letter);
			  current.incrementCount();
		  }
		  else {
			  kids.put(letter, new TrieNode());
			  current = kids.get(letter);
		  }
	  }

	  current.setEndOfWord(true);

	}

	public boolean contains(String word) {
		TrieNode current = root;
		for (char letter : word.toCharArray()) { //gives me each letter of the typed word
			Map<Character, TrieNode> kids = current.getChildren();
			if (!kids.containsKey(letter))
				return false;

			current = kids.get(letter);
		}

		if(current.isEndOfWord())
			return true;
	  	return false;

	}

	public char mostLikelyNextChar(String word) {
		TrieNode current = root;
		Map<Character, TrieNode> kids = new HashMap<>();
		char let = '_';
		int most = 0;

		for (char letter : word.toCharArray()) {
			kids = current.getChildren();
			if (kids.containsKey(letter))
				current = kids.get(letter);
			else
				return '_';
		}

		kids = current.getChildren();

		for (Map.Entry<Character, TrieNode> entry: kids.entrySet()) {
			if (most < entry.getValue().getCount()) {
				most = entry.getValue().getCount();
				let  = entry.getKey();
			}
		}

		return let;

	}

	public static void main(String[]args) {
		Trie t = new Trie();
		t.add("test");
		t.add("text");
		t.add("apple");
		System.out.println(t.root.getChildren());
		System.out.println("Contains \"text\" -> " + t.contains("text"));
		System.out.println("Contains \"tina\" -> " + t.contains("tina"));
		System.out.println("Contains \"te\" -> " + t.contains("te"));


	}

	/***********   INNER CLASS ****/
	class TrieNode {
		private final Map<Character, TrieNode> children;
		private boolean endOfWord;
		private int count;

		public TrieNode(){
		  children = new HashMap<>();
		  endOfWord = false;
		  count = 1;
		}

		private Map<Character, TrieNode> getChildren() {
			return children;
		}

		private void incrementCount(){
		  count++;
		}

		private int getCount(){
		  return count;
		}

		private boolean isEndOfWord() {
			return endOfWord;
		}

		private void setEndOfWord(boolean endOfWord) {
			this.endOfWord = endOfWord;
		}

		public String toString(){
		  return "("+count+")";
		}

	}


}