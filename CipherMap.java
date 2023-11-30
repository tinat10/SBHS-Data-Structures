/*   This code will randomly generate a map that can be used
to perform a simple substitution cipher.

A substitution cipher obscures a message by "subsstituting"
characters with something else.   In our case it will be
another letter.

Imagine you have the following pair swaps (a<->d,j<->e,
c<->v,o<->p]  Then the String

"java code"  is encoded as  "edcd vpaj"

Because the pairings are two-way the process can be reversed

You will us a HashMap<Character,Character> to store these
pairs.  For each entry in the map you will also make the
inverse entry, so if a->r is in the map r->a should be too.
*/
import java.util.*;
import java.io.*;

public class CipherMap {

  	public HashMap<Character,Character> map;
    public static final String OUTPUT_FILE = "LetterCombos.txt";  // serialization

  // No parameter constructor creates a random map.
  public CipherMap(){

    ArrayList<Character> abc = new ArrayList<Character>();
    for (char c = 'a'; c <= 'z'; c++)
        abc.add(c);

    map = new HashMap<>();

    while (abc.size()!=0) {
		char rand = abc.remove((int)(Math.random()*abc.size()));
		char rand2 =  abc.remove((int)(Math.random()*abc.size()));
		map.put(rand, rand2);
		map.put(rand2, rand);
	}

	//System.out.println(map);

  }

  // Write an overloaded constructor that takes a String filename
  // and loads the map from a serialized text file.
  public CipherMap(String fileName){
	map = new HashMap<>();
	try {
	      FileInputStream fileInput = new FileInputStream(fileName);
	      ObjectInputStream objectInput = new ObjectInputStream(fileInput);
	      map = (HashMap<Character,Character>)objectInput.readObject();
	      objectInput.close();
	      fileInput.close();
	    }
		catch (Exception obj1) {
			obj1.printStackTrace();
			return;
	}
  }


  public void serialize(String fileName){ //saving

		try {
	    	FileOutputStream myFileOutStream = new FileOutputStream(fileName);
	   		ObjectOutputStream myObjectOutStream = new ObjectOutputStream(myFileOutStream);
	   		myObjectOutStream.writeObject(map);
			myObjectOutStream.close();
			myFileOutStream.close();
	    }
	    catch (IOException e) {
	    	e.printStackTrace();
        }
  }

  // Write a method called encode that will encode or decode
  // a String value based on the the map
  public String encode(String s){
    String encoded = "";

	for (int i = 0; i<s.length(); i++) {
		if (s.charAt(i) == ' ')
			encoded+=' ';
		else
			encoded+=(map.get(s.charAt(i)));
	}

    return encoded;
  }
  // Use the main message to encode/decode serialize/deserialize

  public static void main (String[]args){
	  CipherMap cm = new CipherMap();;
	  cm.serialize("LetterCombos.txt");
	  String cipherText = cm.encode("happy lunar new year bruh");
	  System.out.println("ENCODED: " + cipherText);
	  System.out.println("DECODED: " + cm.encode(cipherText) + "\n");

	  CipherMap cm2 = new CipherMap("CipherFile.txt"); //CipherMap();
	  System.out.println("ufyer vfhhm hrri m tdvvre tfmb");
	  String cipherText1 = cm2.encode("ufyer vfhhm hrri m tdvvre tfmb");
	  System.out.println(cipherText1);

  }

}
